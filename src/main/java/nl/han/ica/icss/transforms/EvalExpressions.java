package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.ConstantReference;
import nl.han.ica.icss.ast.Declaration;

import java.util.HashMap;

public class EvalExpressions implements Transform {
    private HashMap<String, ConstantDefinition> symboltable;

    @Override
    public void apply(AST ast) {
        symboltable = ast.symboltable;

        findConstantDefinitions(ast.root);
        symboltable.values().forEach(ast.root::removeChild);
        symboltable.clear();
    }

    private void findConstantDefinitions(ASTNode currentNode) {
        if (currentNode.getClass().isAssignableFrom(Declaration.class)) {
            Declaration declaration = (Declaration) currentNode;

            if(declaration.expression.getClass().isAssignableFrom(ConstantReference.class)) {
                replaceExpressionInDeclaration(declaration);
            }
        } else {
            currentNode.getChildren().forEach(this::findConstantDefinitions);
        }
    }

    private void replaceExpressionInDeclaration(Declaration declaration) {
        String key = ((ConstantReference) declaration.expression).name;

        declaration.expression = symboltable.get(key).expression;
    }
}
