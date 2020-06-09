// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/example/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BinaryParser}.
 */
public interface BinaryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPAR(BinaryParser.PARContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPAR(BinaryParser.PARContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNOT(BinaryParser.NOTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNOT(BinaryParser.NOTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOR(BinaryParser.ORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOR(BinaryParser.ORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VAR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVAR(BinaryParser.VARContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VAR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVAR(BinaryParser.VARContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AND}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAND(BinaryParser.ANDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AND}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAND(BinaryParser.ANDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterXOR(BinaryParser.XORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitXOR(BinaryParser.XORContext ctx);
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
	 * Enter a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEQ(BinaryParser.EQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEQ(BinaryParser.EQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IMP}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIMP(BinaryParser.IMPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IMP}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIMP(BinaryParser.IMPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INT}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterINT(BinaryParser.INTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitINT(BinaryParser.INTContext ctx);
}