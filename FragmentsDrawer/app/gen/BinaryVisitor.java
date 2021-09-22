// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/logic/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8

    package app.logic.logic.core;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link BinaryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface BinaryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code ImplBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplBlock(BinaryParser.ImplBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OrBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOrBlock(BinaryParser.OrBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AndBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAndBlock(BinaryParser.AndBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParExpr(BinaryParser.ParExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(BinaryParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XorBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXorBlock(BinaryParser.XorBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NotBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotBlock(BinaryParser.NotBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWS(BinaryParser.WSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EOF}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEOF(BinaryParser.EOFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(BinaryParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EqualBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualBlock(BinaryParser.EqualBlockContext ctx);
}