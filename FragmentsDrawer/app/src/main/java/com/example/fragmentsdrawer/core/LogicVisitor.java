// Generated from Logic.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Logic}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LogicVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Logic#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Logic.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Logic#parenthesis}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenthesis(Logic.ParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by {@link Logic#not}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(Logic.NotContext ctx);
}