// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/example/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link binary_algebraParser}.
 */
public interface binary_algebraListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPAR(binary_algebraParser.PARContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPAR(binary_algebraParser.PARContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNOT(binary_algebraParser.NOTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNOT(binary_algebraParser.NOTContext ctx);
	/**
	 * Enter a parse tree produced by the {@code OR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOR(binary_algebraParser.ORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code OR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOR(binary_algebraParser.ORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code VAR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVAR(binary_algebraParser.VARContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VAR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVAR(binary_algebraParser.VARContext ctx);
	/**
	 * Enter a parse tree produced by the {@code AND}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAND(binary_algebraParser.ANDContext ctx);
	/**
	 * Exit a parse tree produced by the {@code AND}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAND(binary_algebraParser.ANDContext ctx);
	/**
	 * Enter a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterXOR(binary_algebraParser.XORContext ctx);
	/**
	 * Exit a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitXOR(binary_algebraParser.XORContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WS}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWS(binary_algebraParser.WSContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WS}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWS(binary_algebraParser.WSContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEQ(binary_algebraParser.EQContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEQ(binary_algebraParser.EQContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IMP}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIMP(binary_algebraParser.IMPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IMP}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIMP(binary_algebraParser.IMPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INT}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterINT(binary_algebraParser.INTContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitINT(binary_algebraParser.INTContext ctx);
}