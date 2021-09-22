// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/logic/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8

    package app.logic.logic.core;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BinaryParser}.
 */
public interface BinaryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code ImplBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterImplBlock(BinaryParser.ImplBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ImplBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitImplBlock(BinaryParser.ImplBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OrBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOrBlock(BinaryParser.OrBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OrBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOrBlock(BinaryParser.OrBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AndBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAndBlock(BinaryParser.AndBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AndBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAndBlock(BinaryParser.AndBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParExpr(BinaryParser.ParExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ParExpr}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParExpr(BinaryParser.ParExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Var}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVar(BinaryParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Var}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVar(BinaryParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XorBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterXorBlock(BinaryParser.XorBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XorBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitXorBlock(BinaryParser.XorBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NotBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNotBlock(BinaryParser.NotBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NotBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNotBlock(BinaryParser.NotBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWS(BinaryParser.WSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWS(BinaryParser.WSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EOF}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEOF(BinaryParser.EOFContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EOF}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEOF(BinaryParser.EOFContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Int}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterInt(BinaryParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Int}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitInt(BinaryParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EqualBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEqualBlock(BinaryParser.EqualBlockContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EqualBlock}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEqualBlock(BinaryParser.EqualBlockContext ctx);
}