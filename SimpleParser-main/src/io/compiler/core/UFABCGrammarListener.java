// Generated from UFABCGrammar.g4 by ANTLR 4.13.2
package io.compiler.core;

	import java.util.ArrayList;
	import java.util.Stack;
	import java.util.HashMap;
	import io.compiler.types.*;
	import io.compiler.core.exceptions.*;
	import io.compiler.core.ast.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link UFABCGrammarParser}.
 */
public interface UFABCGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(UFABCGrammarParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(UFABCGrammarParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void enterDeclaravar(UFABCGrammarParser.DeclaravarContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#declaravar}.
	 * @param ctx the parse tree
	 */
	void exitDeclaravar(UFABCGrammarParser.DeclaravarContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#comando}.
	 * @param ctx the parse tree
	 */
	void enterComando(UFABCGrammarParser.ComandoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#comando}.
	 * @param ctx the parse tree
	 */
	void exitComando(UFABCGrammarParser.ComandoContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdIF}.
	 * @param ctx the parse tree
	 */
	void enterCmdIF(UFABCGrammarParser.CmdIFContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdIF}.
	 * @param ctx the parse tree
	 */
	void exitCmdIF(UFABCGrammarParser.CmdIFContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void enterCmdAttrib(UFABCGrammarParser.CmdAttribContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdAttrib}.
	 * @param ctx the parse tree
	 */
	void exitCmdAttrib(UFABCGrammarParser.CmdAttribContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void enterCmdLeitura(UFABCGrammarParser.CmdLeituraContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdLeitura}.
	 * @param ctx the parse tree
	 */
	void exitCmdLeitura(UFABCGrammarParser.CmdLeituraContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void enterCmdEscrita(UFABCGrammarParser.CmdEscritaContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdEscrita}.
	 * @param ctx the parse tree
	 */
	void exitCmdEscrita(UFABCGrammarParser.CmdEscritaContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#cmdWhile}.
	 * @param ctx the parse tree
	 */
	void enterCmdWhile(UFABCGrammarParser.CmdWhileContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#cmdWhile}.
	 * @param ctx the parse tree
	 */
	void exitCmdWhile(UFABCGrammarParser.CmdWhileContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(UFABCGrammarParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(UFABCGrammarParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void enterTermo(UFABCGrammarParser.TermoContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#termo}.
	 * @param ctx the parse tree
	 */
	void exitTermo(UFABCGrammarParser.TermoContext ctx);
	/**
	 * Enter a parse tree produced by {@link UFABCGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void enterExprl(UFABCGrammarParser.ExprlContext ctx);
	/**
	 * Exit a parse tree produced by {@link UFABCGrammarParser#exprl}.
	 * @param ctx the parse tree
	 */
	void exitExprl(UFABCGrammarParser.ExprlContext ctx);
}