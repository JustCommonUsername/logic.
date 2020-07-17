package com.example.fragmentsdrawer.core;

import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;

import com.example.fragmentsdrawer.MainActivity;
import com.example.fragmentsdrawer.rooms.Equation;
import com.example.fragmentsdrawer.util.Step;
import com.example.fragmentsdrawer.util.StepInterval;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.transformations.qmc.QuineMcCluskeyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EquationFactory {

    private static ArrayList<Step> steps = new ArrayList<>(5);

    public static synchronized Equation construct(String input) throws IllegalLogicEquationException {
        // TODO: Implement construction
        if (input != null && !TextUtils.isEmpty(input)) {
            // TODO: Implement usage
            // Trivial simplification algorithm
            String simplified;

            try {
                simplified = simplify(input);
            } catch (NullPointerException e) {
                throw new IllegalLogicEquationException();
            }

            // Implementing equation simplification
            final FormulaFactory factory = new FormulaFactory();
            final PropositionalParser parser = new PropositionalParser(factory);
            final Formula formula;

            try {
                formula = parser.parse(EquationString.prepareFunctionTo(simplified));
            } catch (ParserException e) {
                /* Handling wrong equation situation.
                In case an equation is wrong, PropositionParser object will throw ParserException.
                In that case it is important to escalate exception further */
                throw new IllegalLogicEquationException();
            }

            final Formula result = QuineMcCluskeyAlgorithm.compute(formula);

            // First step of building an answer - RPN
            ReversePolishNotation source = ReversePolishNotation.getRPNObject(EquationString.prepareFunctionFrom(result.toString()));

            return new Equation(
                    System.currentTimeMillis(),
                    input,
                    "Function",
                    "Quine-McCluskey algorithm",
                    steps,
                    source.CCNF,
                    source.CDNF,
                    EquationString.prepareFunctionFrom(result.toString())
            );
        }
        return null;
    }
    // Method below is needed for trivial function simplification
    private static String simplify(String input) {
        steps.clear();
        boolean notChanged = false;
        Pair<String, String> change;

        // Cycles through changing input, while there are changes
        while (!notChanged) {
            // Building lexer, parser and tree for walking the equation
            ANTLRInputStream stream = new ANTLRInputStream(input);
            LogicTokens lexer = new LogicTokens(stream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            Logic parser = new Logic(tokens);
            ParseTree tree = parser.expr();

            // Initializing walking algorithm
            ParenthesisListener listener = new ParenthesisListener();
            ParseTreeWalker.DEFAULT.walk(listener, tree);

            // Getting a change from listener class and proceeding it
            if ((change = listener.getChange()) != null) {
                final int manA = input.indexOf(change.first), manB = input.lastIndexOf(change.first);

                // Changing the string, which is being simplified, in order to build new parse tree and walk it
                // The part, which was simplified, and simplified main companion are written in Pair<> object
                String simplification = input.replace(/* Changed part */ change.first, /* Its simplified form */ change.second);

                final int changedA = simplification.indexOf(change.second), changedB = simplification.lastIndexOf(change.second);

                if (!simplification.equals(input)) {
                    // Creating step variable for a list
                    final Step step = new Step(new Pair<>(input, simplification), new StepInterval(manA, manB), new StepInterval(changedA, changedB));
                    // Adding changed string 'simplification' as a step of simplifying an equation
                    steps.add(step);
                    // Changing value of input in order to cycle further
                    input = simplification;
                }
            }
            // If changes weren't found, variable 'notChanged' changes its value so as to stop the cycle
            else
                notChanged = true;
        }

        return input;
    }

}


/**<summary>
     * <b>ReversePolishNotation</b> class is responsible for processing given logic equation
     * and building its RPN, Complete Conjunction Normal Form and Complete Disjunction Normal Form.
     * It also is responsible for checking equation and throwing special IllegalLogicEquationException
     * in case equations syntax is wrong. It represents first step of building complete answer for user.
 * </summary>
 * **/
class ReversePolishNotation {
    String RPN, CDNF, CCNF;

    private int iteration = 0;
    private char[] results;

    private Hashtable<Character, Character[]> variables = new Hashtable<>();
    private ArrayList<Character> foundVars = new ArrayList<>(5);

    private LinkedList<Character> stack = new LinkedList<>();
    private Hashtable<Character, Integer> priorities = new Hashtable<>();

    /* Initializing priority hashtable */
    {
        String[][] distribution = {
                {"¬", "4"},
                {"∧", "3"},
                {"∨", "2"},
                {"⇒", "1"},
                {"≡", "0"},
                {"⊕", "0"}
        };
        for (String[] priority: distribution) {
            priorities.put(priority[0].charAt(0), Integer.parseInt(priority[1]));
        }
    }

    public static ReversePolishNotation getRPNObject(String input) throws IllegalLogicEquationException {
        return new ReversePolishNotation()
                .getRPN(input)
                .prepareLogicTable(0)
                .calculate()
                .getAdditionally();
    }

    ReversePolishNotation() {}

    private ReversePolishNotation getRPN(String input) throws IllegalLogicEquationException {
        StringBuilder res = new StringBuilder();

        try {
            for (Character value : input.toCharArray()) {
                if (value == ' ')
                    continue;
                else if (value == '1' || value == '0')
                    res.append(value);
                else if (value >= 'A' && value <= 'Z') {
                    if (variables != null && !variables.contains(value)) {
                        foundVars.add(value);
                    }
                    res.append(value);
                } else {
                    switch (value) {
                        case '¬':
                        case '∧':
                        case '∨':
                        case '⇒':
                        case '≡':
                        case '⊕':
                            while (!stack.isEmpty()) {
                                if (stack.getLast() == '(' || stack.getLast() == ')' || priorities.get(value) > priorities.get(stack.getLast()))
                                    break;
                                res.append(stack.pop());
                            }
                            stack.push(value);
                            break;
                        case '(':
                            stack.push(value);
                            break;
                        case ')':
                            while (stack.size() > 0 && stack.getFirst() != '(')
                                res.append(stack.pop());
                            if (stack.isEmpty())
                                throw new IllegalLogicEquationException();
                            else
                                stack.pop();
                            break;
                        default:
                            throw new IllegalLogicEquationException();
                    }
                }
            }
            while (!stack.isEmpty()) {
                if (stack.getLast() == '(')
                    throw new IllegalLogicEquationException();
                res.append(stack.pop());
            }

            for (int i = foundVars.size() - 1; i >= 0; i--) {
                variables.put(foundVars.get(i), new Character[(int) Math.pow(2, foundVars.size())]);
            }

            results = new char [foundVars.size()];

            this.RPN = res.toString();
            return this;
        } catch (NoSuchElementException e) {
            throw new IllegalLogicEquationException();
        }
    }

    private ReversePolishNotation calculate() throws IllegalLogicEquationException {
        boolean output;
        int amountOfRows = (int)Math.pow(2, foundVars.size());
        Character[] answers = new Character [amountOfRows];

        stack.clear();

        try {
            for (int row = 0; row < amountOfRows; row++) {
                for (char value : RPN.toCharArray()) {
                    if (value >= 'A' && value <= 'Z') {
                        stack.push(variables.get(value)[row]);
                    } else {
                        char ch = stack.pop();
                        switch (value) {
                            case '¬':
                                output = ch == '0';
                                break;
                            case '∧':
                                output = stack.pop() == '1' && ch == '1';
                                break;
                            case '∨':
                                output = stack.pop() == '1' || ch == '1';
                                break;
                            case '⇒':
                                output = !(stack.pop() == '1' && ch == '0');
                                break;
                            case '≡':
                                output = stack.pop() == ch;
                                break;
                            case '⊕':
                                output = stack.pop() != ch;
                                break;
                            default:
                                throw new IllegalLogicEquationException();
                        }
                        stack.push(output ? '1' : '0');
                    }
                }

                // Left value in the stack is needed answer
                if (stack.size() == 1)
                    answers[row] = stack.pop();
                else
                    throw new IllegalLogicEquationException();
            }
        } catch (NoSuchElementException e) {
            throw new IllegalLogicEquationException();
        }

        variables.put('F', answers);
        return this;
    }

    private ReversePolishNotation getAdditionally() {
        if (RPN.length() > 1) {
            StringBuilder CDNF = new StringBuilder();
            StringBuilder CCNF = new StringBuilder();

            for (int row = 0; row < (int)Math.pow(2, foundVars.size()); row++) {
                boolean isTrue = variables.get('F')[row] == '1';

                if (isTrue) {
                    for (Object ch: foundVars.toArray())
                        CDNF.append((variables.get(ch)[row] == '0' ? "¬" : "") + ch + "∧");
                    CDNF.deleteCharAt(CDNF.length() - 1).append("∨");
                } else {
                    CCNF.append("(");
                    for (Object ch: foundVars.toArray())
                        CCNF.append((variables.get(ch)[row] == '1' ? "¬" : "") + ch + "∨");
                    CCNF.deleteCharAt(CCNF.length() - 1).append(")∧");
                }
            }

            try {
                this.CDNF = CDNF.deleteCharAt(CDNF.length() - 1).toString();
                this.CCNF = CCNF.deleteCharAt(CCNF.length() - 1).toString();
            } catch (Exception e) {
                return this;
            }
        } else {
            CDNF = RPN;
            CCNF = RPN;
        }
        return this;
    }

    private ReversePolishNotation prepareLogicTable(int indx) {
        try {
            if (indx == foundVars.size()) {
                for (int i = 0; i < foundVars.size(); i++) {
                    /**
                     * <summary>Code below is responsible for inserting
                     * data, which was organised during function process, to the dictionary,
                     * so that this data will be used further.
                     *
                     * Insertion proceeds one row at a time</summary>
                     * **/
                    variables.get(foundVars.get(i))[iteration] = results[i];
                }
                iteration++;
                return this;
            }
            for (int i = 0; i < 2; i++) {
                results[indx] = Character.forDigit(i, 10);
                this.prepareLogicTable(indx + 1);
            }
        } catch (NullPointerException e) {
            System.out.println("Bad RPN calculation");
        }

        return this;
    }

    @Override
    public String toString() {
        return "RPN: " + this.RPN + ",\nCDNF: " + this.CDNF + ",\nCCNF: " + this.CCNF;
    }

}
