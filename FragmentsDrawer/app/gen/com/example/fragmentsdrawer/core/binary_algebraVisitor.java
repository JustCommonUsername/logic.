// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/example/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8
package com.example.fragmentsdrawer.core;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link binary_algebraParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface binary_algebraVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPAR(binary_algebraParser.PARContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NOT}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNOT(binary_algebraParser.NOTContext ctx);
	/**
	 * Visit a parse tree produced by the {@code OR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOR(binary_algebraParser.ORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code VAR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVAR(binary_algebraParser.VARContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AND}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAND(binary_algebraParser.ANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXOR(binary_algebraParser.XORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WS}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWS(binary_algebraParser.WSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEQ(binary_algebraParser.EQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IMP}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIMP(binary_algebraParser.IMPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link binary_algebraParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINT(binary_algebraParser.INTContext ctx);
}