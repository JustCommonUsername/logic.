// Generated from Logic.g4 by ANTLR 4.8
package app.logic.logic.core.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LogicParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogicVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(LogicParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolean(LogicParser.BooleanContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(LogicParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(LogicParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code implication}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplication(LogicParser.ImplicationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(LogicParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xor}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXor(LogicParser.XorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code whitespace}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhitespace(LogicParser.WhitespaceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(LogicParser.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code equality}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(LogicParser.EqualityContext ctx);
}