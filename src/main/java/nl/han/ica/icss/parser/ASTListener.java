package nl.han.ica.icss.parser;

import java.util.Stack;

import nl.han.ica.icss.ast.*;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

	//Accumulator attributes:
	private AST ast;
	private Stack<ASTNode> currentContainer;

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
    public void enterVariableDef(ICSSParser.VariableDefContext ctx) {
	    currentContainer.push(new ConstantDefinition());
    }

    @Override
    public void exitVariableDef(ICSSParser.VariableDefContext ctx) {
        handleExit();
    }

    @Override
    public void enterVariableKey(ICSSParser.VariableKeyContext ctx) {
        currentContainer.push(new ConstantReference(ctx.VARIABLE_NAME().toString()));
    }

    @Override
    public void exitVariableKey(ICSSParser.VariableKeyContext ctx) {
        handleExit();
    }

	@Override
	public void enterStylerule(ICSSParser.StyleruleContext ctx) {
		currentContainer.push(new Stylerule());
	}

	@Override
	public void exitStylerule(ICSSParser.StyleruleContext ctx) {
		handleExit();
	}

	@Override
	public void enterTagSelector(ICSSParser.TagSelectorContext ctx) {
		currentContainer.push(new TagSelector(ctx.ELEMENT().toString()));
	}

	@Override
	public void exitTagSelector(ICSSParser.TagSelectorContext ctx) {
		handleExit();
	}

	@Override
    public void enterClassSelector(ICSSParser.ClassSelectorContext ctx) {
        currentContainer.push(new ClassSelector(ctx.ELEMENT().toString()));
    }

    @Override
    public void exitClassSelector(ICSSParser.ClassSelectorContext ctx) {
        handleExit();
    }

    @Override
    public void enterIdSelector(ICSSParser.IdSelectorContext ctx) {
        currentContainer.push(new IdSelector(ctx.ELEMENT().toString()));
    }

    @Override
    public void exitIdSelector(ICSSParser.IdSelectorContext ctx) {
        handleExit();
    }

    @Override
    public void enterWidthDeclaration(ICSSParser.WidthDeclarationContext ctx) {
        pushDeclaration(ctx.WIDTH_KW().toString());
    }

    @Override
    public void exitWidthDeclaration(ICSSParser.WidthDeclarationContext ctx) {
        handleExit();
    }

    @Override
    public void enterAmountPx(ICSSParser.AmountPxContext ctx) {
	    int amount = Integer.parseInt(ctx.NUMBER().toString());
	    currentContainer.push(new PixelLiteral(amount));
    }

    @Override
    public void exitAmountPx(ICSSParser.AmountPxContext ctx) {
	    handleExit();
    }

    @Override
    public void enterColorDeclaration(ICSSParser.ColorDeclarationContext ctx) {
	    pushDeclaration(ctx.COLOR_KEY().toString());
    }

    @Override
    public void exitColorDeclaration(ICSSParser.ColorDeclarationContext ctx) {
	    handleExit();
    }

    @Override
    public void enterHexVal(ICSSParser.HexValContext ctx) {
	    currentContainer.push(new ColorLiteral(ctx.HEX_AMOUNT().toString()));
    }

    @Override
    public void exitHexVal(ICSSParser.HexValContext ctx) {
	    handleExit();
    }

	private void handleExit() {
		ASTNode currentNode = currentContainer.pop();
		(currentContainer.peek()).addChild(currentNode);
	}

	private void pushDeclaration(String property) {
        Declaration declaration = new Declaration();
        declaration.property = property;

        currentContainer.push(declaration);
    }
}
