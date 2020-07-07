package com.example.fragmentsdrawer.core;

import android.util.Log;
import android.util.Pair;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.logicng.formulas.Formula;
import org.logicng.formulas.FormulaFactory;
import org.logicng.io.parsers.ParserException;
import org.logicng.io.parsers.PropositionalParser;
import org.logicng.transformations.qmc.QuineMcCluskeyAlgorithm;

import java.util.HashSet;
import java.util.Stack;

public class ParenthesisListener extends BinaryBaseListener {

    private int scopes = 0;
    private boolean changed = false;
    private Pair<String, String> change = null;

    public Pair<String, String> getChange() {
        return change;
    }

    @Override
    public void exitParExpr(BinaryParser.ParExprContext ctx) {
        /* if (!changed) {
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
        } */
        // Debugging the Intervals
        Log.d("Parenthesis out stream", ctx.start.getInputStream().getText(new Interval(ctx.start.getStartIndex(), ctx.stop.getStopIndex())));
    }

}
