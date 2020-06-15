package com.example.fragmentsdrawer.core;

import com.example.fragmentsdrawer.rooms.Equation;

import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.transformations.qmc.QuineMcCluskeyAlgorithm;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class EquationFactory {

    public static Equation construct(String input) throws IllegalLogicEquationException {
        // TODO: Implement construction
        if (input != null) {
            ReversePolishNotation source = ReversePolishNotation.getRPNObject(input);

            // Implementing equation simplification
            final FormulaFactory factory = new FormulaFactory();
            final PropositionalParser parser = new PropositionalParser(factory);
            final Formula formula;

            try {
                formula = parser.parse(prepareFunctionTo(input));
            } catch (ParserException e) {
                throw new IllegalLogicEquationException();
            }

            final Formula result = QuineMcCluskeyAlgorithm.compute(formula);

            return new Equation(input,
                    "Function", "Quine-McCluskey algorithm", null,
                    source.CDNF, source.CCNF, prepareFunctionFrom(result.toString()));
        }
        return null;
    }

    /* TODO: Needed for testing parsing method, implement


    public static void printTreeNodes(String input) {
        ANTLRInputStream stream = new ANTLRInputStream(input);
        BinaryLexer lexer = new BinaryLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        BinaryParser parser = new BinaryParser(tokens);
        parser.setBuildParseTree(true);
    }
    */

    private static String prepareFunctionTo(String inputString) {
        return inputString
                .replaceAll("¬", "~")
                .replaceAll("∧", "&")
                .replaceAll("∨", "|")
                .replaceAll("⇒", "=>")
                .replaceAll("≡", "<=>");
    }

    private static String prepareFunctionFrom(String inputString) {
        return inputString
                .replaceAll("!", "¬")
                .replaceAll("&", "∧")
                .replaceAll("[|]", "∨")
                .replaceAll("<=>,", "≡")
                .replaceAll("=>", "⇒");
    }

}

class ReversePolishNotation {
    String RPN, CDNF, CCNF;

    private int iteration = 0;
    private char[] results;

    private Hashtable<Character, Character[]> variables = new Hashtable<>();
    private LinkedList<Character> foundVars = new LinkedList<>();

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
            priorities.put(priority[0].toCharArray()[0], Integer.parseInt(priority[1]));
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
                        foundVars.push(value);
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
                    CCNF.append(")∧");
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
