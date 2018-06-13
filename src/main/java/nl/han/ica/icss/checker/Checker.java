package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ConstantDefinition;

import java.util.HashMap;

public class Checker {

    private HashMap<String, ConstantDefinition> symboltable;

    public void check(AST ast) {

        symboltable = new HashMap<>();

        findConstantDefinitions(ast.root);

        ast.symboltable = symboltable;

        if (ast.getErrors().isEmpty()) {
            ast.checked = true;
        }
    }

    private void findConstantDefinitions(ASTNode currentNode) {

        if (currentNode.getClass().isAssignableFrom(ConstantDefinition.class)) {
            ConstantDefinition definition = (ConstantDefinition) currentNode;
            String key = definition.name.name;

            if(symboltable.containsKey(key)) {
                currentNode.setError("Key is already declared (" + key + ")");
            } else {
                symboltable.put(definition.name.name, definition);
            }
        }

        for (ASTNode child : currentNode.getChildren()) {
            findConstantDefinitions(child);
        }
    }
}
