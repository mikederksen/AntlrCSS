package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.ConstantReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Checker {

    private HashMap<String, ConstantDefinition> symboltable;
    private HashMap<String, List<ASTNode>> constantreferences;

    public void check(AST ast) {

        symboltable = new HashMap<>();
        constantreferences = new HashMap<>();

        findConstantDefinitions(ast.root);
        findConstantReferences(ast.root);

        ast.symboltable = symboltable;
        ast.constantReferences = constantreferences;

        if (ast.getErrors().isEmpty()) {
            ast.checked = true;
        }
    }

    private void findConstantDefinitions(ASTNode currentNode) {

        if (currentNode != null) {

            if (currentNode.getClass().isAssignableFrom(ConstantDefinition.class)) {

                ConstantDefinition definition = (ConstantDefinition) currentNode;
                String key = definition.name.name;

                if (symboltable.containsKey(key)) {
                    currentNode.setError("Constant is already declared (" + key + ")");
                } else {
                    symboltable.put(definition.name.name, definition);
                }
            }

            for (ASTNode child : currentNode.getChildren()) {
                findConstantDefinitions(child);
            }
        }
    }

    private void findConstantReferences(ASTNode currentNode) {

        if (currentNode != null) {
            if (!(currentNode instanceof ConstantDefinition)) {
                for (ASTNode child : currentNode.getChildren()) {
                    if (child instanceof ConstantReference) {
                        saveConstantReference((ConstantReference) child, currentNode);
                    } else {
                        findConstantReferences(child);
                    }
                }
            } else {
                currentNode.getChildren().forEach(this::findConstantReferences);
            }
        }
    }

    private void saveConstantReference(ConstantReference reference, ASTNode referenceParent) {

        String key = reference.name;

        if (!symboltable.containsKey(key)) {
            reference.setError("Constant is not declared yet");
        } else if (constantreferences.containsKey(key)) {
            constantreferences.get(key).add(referenceParent);
        } else {
            List<ASTNode> declarations = new ArrayList<>();
            declarations.add(referenceParent);

            constantreferences.put(key, declarations);
        }
    }
}
