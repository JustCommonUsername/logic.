package com.example.fragmentsdrawer.core;

import android.util.Log;
import android.util.Pair;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.Interval;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.transformations.qmc.QuineMcCluskeyAlgorithm;

public class ParenthesisListener extends LogicBaseListener {

    private boolean changed = false;
    private Pair<String, String> change = null;

    public Pair<String, String> getChange() {
        return change;
    }

    @Override
    public void exitParenthesis(Logic.ParenthesisContext ctx) {
        if (!changed) {
            final CharStream input = ctx.start.getInputStream();

            final int a = ctx.start.getStartIndex();
            final int b = ctx.stop.getStopIndex();

            final Interval interval = new Interval(a, b);

            final String branch = input.getText(interval);

            final FormulaFactory factory = new FormulaFactory();
            final PropositionalParser parser = new PropositionalParser(factory);
            Formula formula = null;

            try {
                formula = parser.parse(EquationString.prepareFunctionTo(branch));
            } catch (ParserException e) {
                e.printStackTrace();
                Log.e("BINARY_PARSER algorithm", "Wrong formula in branch: " + branch);
            }

            if (formula != null) {
                String simplified = EquationString.prepareFunctionFrom(
                        QuineMcCluskeyAlgorithm
                                .compute(formula)
                                .toString());

                if (!(simplified = "(" + simplified + ")").equals(branch)) {
                    change = new Pair<>(branch, simplified);
                    changed = true;
                }
            }
        }
    }

}
