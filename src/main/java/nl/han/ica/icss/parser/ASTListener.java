package nl.han.ica.icss.parser;

import java.util.Stack;

import nl.han.ica.icss.ast.*;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {
	// - Stylesheet
	// 	- StyleRule (*)
	//   - Selector
	//   - Declaration (*)
	//    - Value
	//    - Expression

	//Accumulator attributes:
	private AST ast;
	private Stack<ASTNode> currentContainer; //This is a hint...

	public ASTListener() {
		ast = new AST();
		currentContainer = new Stack<>();
	}

    public AST getAST() {
        return ast;
    }

	@Override
	public void enterStylesheet(ICSSParser.StylesheetContext ctx) {
		currentContainer.push(new Stylesheet());
	}

	@Override
	public void exitStylesheet(ICSSParser.StylesheetContext ctx) {
		ast.setRoot((Stylesheet)currentContainer.pop());
	}

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		currentContainer.push(new Stylerule());
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		Stylerule currentStylerule = (Stylerule)currentContainer.pop();
		(currentContainer.peek()).addChild(currentStylerule);
	}



	@Override
	public void enterSelector(ICSSParser.SelectorContext ctx) {
		System.out.println("Entering selector: " + ctx.getText());


	}

	@Override
	public void exitSelector(ICSSParser.SelectorContext ctx) {
		System.out.println("Exiting selector: " + ctx.getText());
	}

	@Override
	public void enterDeclaration(ICSSParser.DeclarationContext ctx) {
		System.out.println("Entering declaration: " + ctx.getText());
	}

	@Override
	public void exitDeclaration(ICSSParser.DeclarationContext ctx) {
		System.out.println("Exiting declaration: " + ctx.getText());
	}
}
