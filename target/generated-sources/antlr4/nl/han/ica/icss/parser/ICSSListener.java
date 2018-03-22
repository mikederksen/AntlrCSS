// Generated from nl/han/ica/icss/parser/ICSS.g4 by ANTLR 4.7
package nl.han.ica.icss.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ICSSParser}.
 */
public interface ICSSListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void enterStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#stylesheet}.
	 * @param ctx the parse tree
	 */
	void exitStylesheet(ICSSParser.StylesheetContext ctx);
	/**
	 * Enter a parse tree produced by {@link ICSSParser#class}.
	 * @param ctx the parse tree
	 */
	void enterClass(ICSSParser.ClassContext ctx);
	/**
	 * Exit a parse tree produced by {@link ICSSParser#class}.
	 * @param ctx the parse tree
	 */
	void exitClass(ICSSParser.ClassContext ctx);
}