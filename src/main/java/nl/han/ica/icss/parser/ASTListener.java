package nl.han.ica.icss.parser;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.AddOperation;
import nl.han.ica.icss.ast.ClassSelector;
import nl.han.ica.icss.ast.ColorLiteral;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.ConstantReference;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.IdSelector;
import nl.han.ica.icss.ast.MultiplyOperation;
import nl.han.ica.icss.ast.PixelLiteral;
import nl.han.ica.icss.ast.ScalarLiteral;
import nl.han.ica.icss.ast.Stylerule;
import nl.han.ica.icss.ast.Stylesheet;
import nl.han.ica.icss.ast.SubtractOperation;
import nl.han.ica.icss.ast.SwitchDefaultCase;
import nl.han.ica.icss.ast.SwitchValueCase;
import nl.han.ica.icss.ast.Switchrule;
import nl.han.ica.icss.ast.TagSelector;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Stack;

/**
 * This class extracts the ICSS Abstract Syntax Tree from the Antlr Parse tree.
 */
public class ASTListener extends ICSSBaseListener {

	private AST ast;
	private Stack<ASTNode> currentContainer;

	public ASTListener() {
		ast = new AST();
		currentContainer = new Stack<>();
	}

    public AST getAST() {
        return ast;
    }

    // Stylesheet
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
        handleExit();
    }

    // Constants
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

    // Switch
    @Override
    public void enterSwitchRule(ICSSParser.SwitchRuleContext ctx) {
        currentContainer.push(new Switchrule());
    }

    @Override
    public void exitSwitchRule(ICSSParser.SwitchRuleContext ctx) {
        handleExit();
    }

    @Override
    public void enterSwitchCase(ICSSParser.SwitchCaseContext ctx) {
        currentContainer.push(new SwitchValueCase());
    }

    @Override
    public void exitSwitchCase(ICSSParser.SwitchCaseContext ctx) {
        handleExit();
    }

    @Override
    public void enterSwitchDefault(ICSSParser.SwitchDefaultContext ctx) {
        currentContainer.push(new SwitchDefaultCase());
    }

    @Override
    public void exitSwitchDefault(ICSSParser.SwitchDefaultContext ctx) {
	    handleExit();
    }

    // Selectors
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

    // Declarations
    @Override
    public void enterWidthDeclaration(ICSSParser.WidthDeclarationContext ctx) {
        pushDeclaration(ctx.WIDTH_KW().toString());
    }

    @Override
    public void exitWidthDeclaration(ICSSParser.WidthDeclarationContext ctx) {
        handleExit();
    }

    @Override
    public void enterHeightDeclaration(ICSSParser.HeightDeclarationContext ctx) {
        pushDeclaration(ctx.HEIGHT_KW().toString());
    }

    @Override
    public void exitHeightDeclaration(ICSSParser.HeightDeclarationContext ctx) {
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

    // Values
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
    public void enterAmountScalar(ICSSParser.AmountScalarContext ctx) {
        currentContainer.push(new ScalarLiteral(ctx.NUMBER().toString()));
    }

    @Override
    public void exitAmountScalar(ICSSParser.AmountScalarContext ctx) {
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

    // Calculation
    @Override
    public void enterValueExpression(ICSSParser.ValueExpressionContext ctx) {
        if(isCalculation(ctx)) {
            ParseTree operatorChild = ctx.children.get(1);
            String operator = operatorChild.getText();

            switch (operator) {
                case "*":
                    currentContainer.push(new MultiplyOperation());
                    break;

                case "+":
                    currentContainer.push(new AddOperation());
                    break;

                case "-":
                    currentContainer.push(new SubtractOperation());
                    break;

                default:
                    throw new UnsupportedOperationException();
            }
        }
    }

    @Override
    public void exitValueExpression(ICSSParser.ValueExpressionContext ctx) {
        if(isCalculation(ctx)) {
            handleExit();
        }
    }

    private boolean isCalculation(ParserRuleContext ctx) {
        return ctx.children != null && ctx.children.size() > 1;
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
