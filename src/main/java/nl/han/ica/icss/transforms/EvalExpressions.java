package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.Declaration;
import nl.han.ica.icss.ast.Operation;

public class EvalExpressions implements Transform {

    @Override
    public void apply(AST ast) {
        calculateOperations(ast.root);
    }

    private void calculateOperations(ASTNode currentNode) {
        if (currentNode != null) {

            if (currentNode instanceof Declaration) {
                Declaration declaration = (Declaration)currentNode;
                if (declaration.expression instanceof Operation) {
                    declaration.expression = ((Operation)declaration.expression).calculate();
                }
            } else {
                currentNode.getChildren().forEach(this::calculateOperations);
            }
        }
    }
}
