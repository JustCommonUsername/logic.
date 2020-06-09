// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/example/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
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
	 * Visit a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNOT(BinaryParser.NOTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOR(BinaryParser.ORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VARIABLE}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVARIABLE(BinaryParser.VARIABLEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WHITESPACE}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWHITESPACE(BinaryParser.WHITESPACEContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EQUAL}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEQUAL(BinaryParser.EQUALContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AND}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAND(BinaryParser.ANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code PARENTHESIS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPARENTHESIS(BinaryParser.PARENTHESISContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXOR(BinaryParser.XORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IMPLICATION}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIMPLICATION(BinaryParser.IMPLICATIONContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INTEGER}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINTEGER(BinaryParser.INTEGERContext ctx);
}