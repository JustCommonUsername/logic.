// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/example/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BinaryParser}.
 */
public interface BinaryListener extends ParseTreeListener {
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
	 * Enter a parse tree produced by the {@code VARIABLE}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterVARIABLE(BinaryParser.VARIABLEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code VARIABLE}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitVARIABLE(BinaryParser.VARIABLEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WHITESPACE}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterWHITESPACE(BinaryParser.WHITESPACEContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WHITESPACE}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitWHITESPACE(BinaryParser.WHITESPACEContext ctx);
	/**
	 * Enter a parse tree produced by the {@code EQUAL}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterEQUAL(BinaryParser.EQUALContext ctx);
	/**
	 * Exit a parse tree produced by the {@code EQUAL}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitEQUAL(BinaryParser.EQUALContext ctx);
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
	 * Enter a parse tree produced by the {@code PARENTHESIS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPARENTHESIS(BinaryParser.PARENTHESISContext ctx);
	/**
	 * Exit a parse tree produced by the {@code PARENTHESIS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPARENTHESIS(BinaryParser.PARENTHESISContext ctx);
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
	 * Enter a parse tree produced by the {@code IMPLICATION}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterIMPLICATION(BinaryParser.IMPLICATIONContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IMPLICATION}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitIMPLICATION(BinaryParser.IMPLICATIONContext ctx);
	/**
	 * Enter a parse tree produced by the {@code INTEGER}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterINTEGER(BinaryParser.INTEGERContext ctx);
	/**
	 * Exit a parse tree produced by the {@code INTEGER}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitINTEGER(BinaryParser.INTEGERContext ctx);
}