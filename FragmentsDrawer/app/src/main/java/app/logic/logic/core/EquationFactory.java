package app.logic.logic.core;

import android.text.TextUtils;
import android.util.Pair;

import app.logic.logic.core.antlr.EquationErrorListener;
import app.logic.logic.core.antlr.EvaluationVisitor;
import app.logic.logic.core.antlr.LogicLexer;
import app.logic.logic.core.antlr.LogicParser;
import app.logic.logic.core.antlr.ParenthesisListener;
import app.logic.logic.core.antlr.TransformationVisitor;
import app.logic.logic.core.nf.ConjunctionalNF;
import app.logic.logic.core.nf.DisjunctionalNF;
import app.logic.logic.rooms.Equation;
import app.logic.logic.rooms.EquationDao;
import app.logic.logic.util.Step;
import app.logic.logic.util.StepInterval;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.transformations.qmc.QuineMcCluskeyAlgorithm;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * <summary>
 *     The class below is a <strong>main engine</strong> of the whole program. It is used to construct
 *     {@link Equation} class object, which is then used by {@link EquationDao} object to save
 *     {@link Equation} to the <em>database</em> and which is also used by <strong>application</strong>
 *     to show information about needed <em>equation</em>.
 *
 *     <p>{@link EquationFactory} class contains main methods, needed to <em>simplify</em>,
 *     <em>normalize</em> and <em>analyze</em> the given equation</p>
 *
 *     @see EquationFactory#construct(String)
 *     @see EquationFactory#simplify(String)
 *     @see EquationFactory#normalize(ParseTree)
 *     @see Results
 *     @see Results#getVariableMap(TreeSet)
 * </summary>
 */
public class EquationFactory {

    private ArrayList<Step> steps;

    {
        steps = new ArrayList<>(5);
    }

    /**
     * The <strong>main method</strong> in {@link EquationFactory} class. It is a starting point of
     * a simplification and normalization process, which is needed to provide information
     * to {@link Equation} object. It fires {@link EquationFactory#simplify(String)} and
     * {@link EquationFactory#normalize(ParseTree)} methods, and also creates {@link Results} object,
     * which contains thorough information about <em>given logic equation</em>, containing
     * {@code disjunctional normal form}, {@code conjunctional normal form} and {@code logic table}
     * information.
     *
     *
     * @param input is a given logic equation, which has to be simplified, normalized and analyzed
     *              by classes, provided by {@code EquationFactory} class. It is used in simplification
     *              method
     *
     * @return Equation object, which is used as a main data class through the whole program
     *
     * @throws IllegalLogicEquationException in case provided logic equation is invalid (wrong grammar)
     */
    public synchronized Equation construct(String input) throws IllegalLogicEquationException {
        if (input != null && !TextUtils.isEmpty(input)) {
            // TODO: Implement usage
            // Trivial simplification algorithm
            final ParseTree tree = simplify(input);
            final Pair<String, TreeSet<String>> resultAndTokens = normalize(tree);

            final String result = resultAndTokens.first;
            final Results results = new Results(resultAndTokens.second, tree);

            final LogicParser parser = new LogicParser(new CommonTokenStream(new LogicLexer(new ANTLRInputStream(result))));
            final TransformationVisitor visitor = new TransformationVisitor();

            return new Equation(
                    System.currentTimeMillis(),
                    input,
                    "Function",
                    "Quine-McCluskey algorithm",
                    steps,
                    results.CNF.toString(),
                    results.DNF.toString(),
                    visitor.visit(parser.expr())
            );
        }
        return null;
    }


    /**
     * Method below is a critical method, needed for <strong>building step-by-step solution</strong>.
     * Its main <em>variables</em> are {@link LogicLexer}, {@link LogicParser}, {@link ParenthesisListener}
     * and {@link Pair} objects.
     *
     * <p>{@link LogicLexer} and {@link LogicParser} objects are generated from {@code Logic.g4}
     * grammar, written in {@code ANTLR} language. {@link LogicLexer} class contains <em>tokens</em>
     * and <em>methods</em>, needed to parse given logic equation. {@link LogicParser} object
     * parses <em>given logic equation</em>. As an output, {@link LogicParser} object provides
     * {@link ParseTree} object, which is a root of a <em>parse tree</em></p>
     *
     * <p>Returned {@link ParseTree} object is then used by {@link ParseTreeWalker} object,
     * which walks its <em>nodes</em> and, in case specific node is found, fires events,
     * which are observed by {@link ParenthesisListener} listener. {@link ParenthesisListener}
     * gets information from given <em>node</em> (mainly, such nodes represent parenthesises
     * in logic equation) and then tries to simplify them. In case of success, {@link ParenthesisListener}
     * stops observing any events, fired by {@link ParseTreeWalker}, and is reade to return
     * information about found <em>change</em></p>
     *
     * <p>{@link ParenthesisListener#getChange()} method returns found change, which is a {@link Pair}
     * object. Its <strong>first</strong> value is unchanged string, and <strong>second</strong> -
     * its simplified equivalent. Found {@code change} is then used to change <em>input</em>
     * information</p>
     *
     * <p>The method cycles through <em>logic equation object</em>, until no change is found. In case
     * {@link ParenthesisListener#getChange()} returns {@code null}, cycle stops.</p>
     *
     *
     * @param input is a given logic equation string
     *
     * @return ParseTree object of a simplified input equation
     *
     * @throws IllegalLogicEquationException in case given input is invalid
     * @throws ParseCancellationException when parse process of an equation was interrupted by SyntaxError
     */
    private ParseTree simplify(String input) throws
            IllegalLogicEquationException, ParseCancellationException {
        steps.clear();
        Pair<String, String> change;

        ANTLRInputStream stream = new ANTLRInputStream(input);
        LogicLexer lexer = new LogicLexer(stream);

        lexer.addErrorListener(new EquationErrorListener());

        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogicParser parser = new LogicParser(tokens);

        ParseTree tree = parser.expr();

        if (new TransformationVisitor().visit(tree).contains("null"))
            throw new IllegalLogicEquationException();

        while (true) {
            lexer.setInputStream(new ANTLRInputStream(input));
            parser.setTokenStream(new CommonTokenStream(lexer));

            tree = parser.expr();

            ParenthesisListener listener = new ParenthesisListener();
            ParseTreeWalker.DEFAULT.walk(listener, tree);

            if ((change = listener.getChange()) != null) {
                final int manA = input.indexOf(change.first), manB = input.lastIndexOf(change.first);

                // Changing the string, which is being simplified, in order to build new parse tree and walk it
                // The part, which was simplified, and simplified main companion are written in Pair<> object
                String simplification = input.replace(/* Changed part */ change.first, /* Its simplified form */ change.second);

                final int changedA = simplification.indexOf(change.second), changedB = simplification.lastIndexOf(change.second);

                if (!simplification.equals(input)) {
                    final Step step = new Step(new Pair<>(input, simplification), new StepInterval(manA, manB), new StepInterval(changedA, changedB));
                    steps.add(step);
                    input = simplification;
                }
            }
            else
                break;
        }

        return tree;
    }


    /**
     * Method below evaluates <strong>normalized</strong> form from given in
     * {@link EquationFactory#construct(String)} method <em>logic equation</em>. It uses
     * {@link FormulaFactory}, {@link PropositionalParser}, {@link Formula} and
     * {@link QuineMcCluskeyAlgorithm} classes, which are provided by <strong>LogicNG</strong>
     * library. It normalizes given <em>logic equation</em> by using <em>QuineMcCluskey Algorithm</em>.
     * In order to be parsed properly, it uses {@link TransformationVisitor} class, which changes
     * <em>logic equation</em> symbols in order to use proper grammar.
     *
     *
     * @param tree is a parsed logic equation tree
     *
     * @return normalized form of a logic equation and a set of variables
     *
     * @throws IllegalLogicEquationException if the given equation doesn't follow grammar
     */
    private Pair<String, TreeSet<String>> normalize(ParseTree tree) throws IllegalLogicEquationException {
        final FormulaFactory factory = new FormulaFactory();
        final PropositionalParser parser = new PropositionalParser(factory);
        final Formula formula, result;

        final TransformationVisitor visitor = new TransformationVisitor();

        final String transformed = visitor.visit(tree);

        if (transformed == null || transformed.contains("null"))
            throw new IllegalLogicEquationException();
        else {
            try {
                formula = parser.parse(transformed);
            } catch (ParserException e) {
                throw new IllegalLogicEquationException();
            }
        }

        result = QuineMcCluskeyAlgorithm.compute(formula);

        return new Pair<>(result.toString(), visitor.getVariableSet());
    }


    /**<summary>
     * Class represents results of logic equation evaluation. It's fields contain information,
     * which helps to analyze most logic functions. Main fields are {@link DisjunctionalNF},
     * {@link ConjunctionalNF} and {@link TreeMap}.
     *
     * <p>{@link DisjunctionalNF} field is a disjunctional normal form, built from given
     * {@link TreeSet} in the constructor</p>
     *
     * <p>{@link ConjunctionalNF} field is a conjunctional normal form, built from given
     * {@link TreeSet} in the constructor</p>
     *
     * <p>{@link TreeMap} field represents table with logical outputs, containing values
     * for every <em>variable</em>, followed by resulting <em>function</em>. Its size equals
     * {@code n + 1}, where <em>n</em> is a number of variables, and the length of its
     * values equals {@code 2^n}</p>
     * </summary>
     * */
    private class Results {

        final DisjunctionalNF DNF;
        final ConjunctionalNF CNF;
        final TreeMap<String, Boolean[]> results;

        /**
         * Constructs new, fully evaluated {@link Results} object.
         *
         *
         * @param variableSet is a set of variables in the logic equation, whose {@code DNF},
         *                    {@code CNF} and {@code results table} have to be provided
         *
         * @param reducedTree is a {@link ParseTree} object, that contains parsed logic equation
         *                    to be analyzed
         * */
        public Results(TreeSet<String> variableSet, ParseTree reducedTree) {
            this.results = getVariableMap(variableSet);

            EvaluationVisitor visitor = new EvaluationVisitor(results, reducedTree);

            final Boolean[] funcResults = new Boolean [getRowsCount(variableSet.size())];

            for (int i = 0; i < getRowsCount(variableSet.size()); i++) {
                funcResults[i] = visitor.visitAtRow(i);
            }

            results.put("Function", funcResults);

            this.DNF = new DisjunctionalNF(results);
            this.CNF = new ConjunctionalNF(results);
        }


        /**
         * Returns {@code TreeMap} object, which represents logic table for given in {@code TreeSet}
         * variables, sorted by value. {@code TreeMap} object doesn't contain information about
         * function results
         *
         *
         * @param variableSet is a set of variables in given logic equation
         *
         * @return {@code TreeMap} object, which represents logic table
         * */
        private TreeMap<String, Boolean[]> getVariableMap(TreeSet<String> variableSet) {
            TreeMap<String, Boolean[]> variableMap = new TreeMap<>();

            final int length = getRowsCount(variableSet.size());
            boolean changeFactor = false;
            int column = length / 2;

            for (String variable: variableSet) {
                Boolean[] results = new Boolean [length];

                for (int i = 0; i < length; i++) {
                    results[i] = changeFactor;
                    changeFactor = (i + 1) % column == 0 ? !changeFactor : changeFactor;
                }

                variableMap.put(variable, results);
                column /= 2;
            }

            return variableMap;
        }

        /**
         * Returns a length of one column in the logic table for given logic equation
         *
         *
         * @param setSize is a number of variables in the given {@code TreeSet}
         *
         * @return length of one <em>column</em> in the logic table
         */
        private int getRowsCount(int setSize) {
            return (int)Math.pow(2, setSize);
        }

    }

}

