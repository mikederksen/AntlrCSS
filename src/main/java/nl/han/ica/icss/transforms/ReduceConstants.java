package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ASTNode;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.ConstantReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReduceConstants implements Transform {

    private HashMap<String, ConstantDefinition> symboltable;

    @Override
    public void apply(AST ast) {
        symboltable = ast.symboltable;

        ast.constantReferences.forEach(this::replaceReferenceInDeclaration);

        symboltable.values().forEach(ast.root::removeChild);
        symboltable.clear();
    }

    private void replaceReferenceInDeclaration(String key, List<ASTNode> referenceParents) {
        for(ASTNode parentNode : referenceParents) {

            List<ASTNode> childrenClone = new ArrayList<>(parentNode.getChildren());

            childrenClone
                    .stream()
                    .filter(c -> c instanceof ConstantReference)
                    .forEach(c -> {
                        parentNode.removeChild(c);
                        parentNode.addChild(symboltable.get(key).expression);
                    });
        }
    }
}
