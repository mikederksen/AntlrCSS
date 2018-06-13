package nl.han.ica.icss.checker;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.ConstantReference;
import nl.han.ica.icss.ast.Declaration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Checker {

    private HashMap<String, ConstantDefinition> symboltable;
    private HashMap<String, List<Declaration>> constantreferences;

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

        if (currentNode.getClass().isAssignableFrom(ConstantDefinition.class)) {
            ConstantDefinition definition = (ConstantDefinition) currentNode;
            String key = definition.name.name;

            if(symboltable.containsKey(key)) {
                currentNode.setError("Constant is already declared (" + key + ")");
            } else {
                symboltable.put(definition.name.name, definition);
            }
        }

        for (ASTNode child : currentNode.getChildren()) {
            findConstantDefinitions(child);
        }
    }

    private void findConstantReferences(ASTNode currentNode) {

        if (currentNode.getClass().isAssignableFrom(Declaration.class)) {
            Declaration declaration = (Declaration) currentNode;

            if(declaration.expression.getClass().isAssignableFrom(ConstantReference.class)) {
                saveConstantReference(declaration);
            }
        } else {
            currentNode.getChildren().forEach(this::findConstantReferences);
        }
    }

    private void saveConstantReference(Declaration declaration) {

        String key = ((ConstantReference) declaration.expression).name;

        if(!symboltable.containsKey(key)) {
            declaration.setError("Constant is not declared yet");
        } else  if(constantreferences.containsKey(key)) {
            constantreferences.get(key).add(declaration);
        } else {
            List<Declaration> declarations = new ArrayList<>();
            declarations.add(declaration);

            constantreferences.put(key, declarations);
        }
    }
}
