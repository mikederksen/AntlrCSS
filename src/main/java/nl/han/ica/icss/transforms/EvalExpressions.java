package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.ConstantReference;
import nl.han.ica.icss.ast.Declaration;

import java.util.HashMap;
import java.util.List;

public class EvalExpressions implements Transform {

    private HashMap<String, ConstantDefinition> symboltable;
    private HashMap<String, List<Declaration>> declarationsForConstant;

    @Override
    public void apply(AST ast) {
        symboltable = ast.symboltable;
        declarationsForConstant = new HashMap<>();


    }

    private void findDeclarationsForConstantReferences() {
        for (String key : symboltable.keySet()) {

        }
    }

    private void findConstantDefinitions(ASTNode currentNode) {

        if (currentNode.getClass().isAssignableFrom(ConstantReference.class)) {
            ConstantReference reference = (ConstantReference) currentNode;

            // Check declaration type and child type reference
            // Add to declarationsForConstants
            // Transform
            if(declarationsForConstant.containsKey(reference))
        }
    }

    private void addNodeDing() {

    }
}
