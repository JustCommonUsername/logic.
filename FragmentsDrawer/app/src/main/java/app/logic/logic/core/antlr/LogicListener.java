// Generated from Logic.g4 by ANTLR 4.8
package app.logic.logic.core.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LogicParser}.
 */
public interface LogicListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot(LogicParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot(LogicParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterBoolean(LogicParser.BooleanContext ctx);
	/**
	 * Exit a parse tree produced by the {@code boolean}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitBoolean(LogicParser.BooleanContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOr(LogicParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOr(LogicParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd(LogicParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd(LogicParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code implication}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterImplication(LogicParser.ImplicationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code implication}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitImplication(LogicParser.ImplicationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code variable}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVariable(LogicParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code variable}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVariable(LogicParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xor}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterXor(LogicParser.XorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xor}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitXor(LogicParser.XorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code whitespace}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWhitespace(LogicParser.WhitespaceContext ctx);
	/**
	 * Exit a parse tree produced by the {@code whitespace}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWhitespace(LogicParser.WhitespaceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(LogicParser.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenthesis}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(LogicParser.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code equality}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEquality(LogicParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by the {@code equality}
	 * labeled alternative in {@link LogicParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEquality(LogicParser.EqualityContext ctx);
}