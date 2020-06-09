// Generated from C:/TemporaryProjects/Temporary folder, Tasks to test, Android/Tasks_to_test_on_08-02-2020/FragmentsDrawer/app/src/main/java/com/example/fragmentsdrawer/core\Binary.g4 by ANTLR 4.8
package com.example.fragmentsdrawer;
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
	 * Visit a parse tree produced by the {@code PAR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPAR(BinaryParser.PARContext ctx);
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
	 * Visit a parse tree produced by the {@code VAR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVAR(BinaryParser.VARContext ctx);
	/**
	 * Visit a parse tree produced by the {@code AND}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAND(BinaryParser.ANDContext ctx);
	/**
	 * Visit a parse tree produced by the {@code XOR}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXOR(BinaryParser.XORContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WS}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWS(BinaryParser.WSContext ctx);
	/**
	 * Visit a parse tree produced by the {@code EQ}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEQ(BinaryParser.EQContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IMP}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIMP(BinaryParser.IMPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code INT}
	 * labeled alternative in {@link BinaryParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitINT(BinaryParser.INTContext ctx);
}