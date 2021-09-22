package app.logic.logic.core.antlr;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.misc.Interval;

import java.util.TreeSet;

public class TransformationVisitor extends LogicBaseVisitor<String> {

    private TreeSet<String> set = new TreeSet<>();

    public TreeSet<String> getVariableSet() {
        return set;
    }

    @Override
    public String visitNot(LogicParser.NotContext ctx) {
        return (ctx.NOT().getSymbol().getText().equals("~") ? "¬" : "~") + visit(ctx.expr());
    }

    @Override
    public String visitBoolean(LogicParser.BooleanContext ctx) {
        final String token = ctx.BOOL().getSymbol().getText();

        if (token.equals("1") || token.equals("0"))
            return token.equals("1") ? "$true" : "$false";
        return token.equals("$true") ? "True" : "False";
    }

    @Override
    public String visitOr(LogicParser.OrContext ctx) {
        return visit(ctx.left) + (ctx.OR().getSymbol().getText().equals("|") ? "∨" : "|") + visit(ctx.right);
    }

    @Override
    public String visitAnd(LogicParser.AndContext ctx) {
        return visit(ctx.left) + (ctx.AND().getSymbol().getText().equals("&") ? "∧" : "&") + visit(ctx.right);
    }

    @Override
    public String visitImplication(LogicParser.ImplicationContext ctx) {
        return visit(ctx.left) + (ctx.IMPL().getSymbol().getText().equals("⇒") ? "=>" : "⇒") + visit(ctx.right);
    }

    @Override
    public String visitVariable(LogicParser.VariableContext ctx) {
        final CharStream stream = ctx.getStart().getInputStream();
        final Interval interval = new Interval(ctx.getStart().getStartIndex(), ctx.getStop().getStopIndex());
        final String var = stream.getText(interval);

        set.add(var);

        return var;
    }

    @Override
    public String visitXor(LogicParser.XorContext ctx) {
        return "~(" + visit(ctx.left) + "<=>" + visit(ctx.right) + ")";
    }

    @Override
    public String visitEquality(LogicParser.EqualityContext ctx) {
        return visit(ctx.left) + (ctx.EQUAL().getSymbol().getText().equals("<=>") ? "≡" : "<=>") + visit(ctx.right);
    }

    @Override
    public String visitWhitespace(LogicParser.WhitespaceContext ctx) {
        return "";
    }

    @Override
    public String visitParenthesis(LogicParser.ParenthesisContext ctx) {
        return "(" + visit(ctx.expr()) + ")";
    }

}
