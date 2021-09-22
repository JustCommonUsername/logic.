package app.logic.logic.core.antlr;

import androidx.annotation.NonNull;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.TreeMap;

public class EvaluationVisitor extends LogicBaseVisitor<Boolean> {

    private TreeMap<String, Boolean[]> variables;
    private ParseTree expr;
    private int row;

    public EvaluationVisitor(@NonNull TreeMap<String, Boolean[]> variables, @NonNull ParseTree expr) {
        this.variables = variables;
        this.expr = expr;
    }

    public boolean visitAtRow(int row) {
        this.row = row;
        return visit(expr);
    }

    @Override
    public Boolean visitNot(LogicParser.NotContext ctx) {
        return !visit(ctx.expr());
    }

    @Override
    public Boolean visitBoolean(LogicParser.BooleanContext ctx) {
        return ctx.BOOL().getSymbol().getText().equals("True") || ctx.BOOL().getSymbol().getText().equals("$true");
    }

    @Override
    public Boolean visitOr(LogicParser.OrContext ctx) {
        return visit(ctx.left) || visit(ctx.right);
    }

    @Override
    public Boolean visitAnd(LogicParser.AndContext ctx) {
        return visit(ctx.left) && visit(ctx.right);
    }

    @Override
    public Boolean visitImplication(LogicParser.ImplicationContext ctx) {
        return !visit(ctx.left) || visit(ctx.right);
    }

    @Override
    public Boolean visitVariable(LogicParser.VariableContext ctx) {
        return variables.get(ctx.VAR().getText())[row];
    }

    @Override
    public Boolean visitXor(LogicParser.XorContext ctx) {
        final boolean left = visit(ctx.left), right = visit(ctx.right);
        return !left && right || left && !right;
    }

    @Override
    public Boolean visitWhitespace(LogicParser.WhitespaceContext ctx) {
        return super.visitWhitespace(ctx);
    }

    @Override
    public Boolean visitParenthesis(LogicParser.ParenthesisContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Boolean visitEquality(LogicParser.EqualityContext ctx) {
        final boolean left = visit(ctx.left), right = visit(ctx.right);
        return left && right || !left && !right;
    }
}
