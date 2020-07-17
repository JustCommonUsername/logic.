// Generated from Logic.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Logic}.
 */
public interface LogicListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Logic#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Logic.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Logic#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Logic.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Logic#parenthesis}.
	 * @param ctx the parse tree
	 */
	void enterParenthesis(Logic.ParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by {@link Logic#parenthesis}.
	 * @param ctx the parse tree
	 */
	void exitParenthesis(Logic.ParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by {@link Logic#not}.
	 * @param ctx the parse tree
	 */
	void enterNot(Logic.NotContext ctx);
	/**
	 * Exit a parse tree produced by {@link Logic#not}.
	 * @param ctx the parse tree
	 */
	void exitNot(Logic.NotContext ctx);
}