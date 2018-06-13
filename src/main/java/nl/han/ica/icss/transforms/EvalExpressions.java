package nl.han.ica.icss.transforms;

import nl.han.ica.icss.ast.AST;
import nl.han.ica.icss.ast.ConstantDefinition;
import nl.han.ica.icss.ast.Declaration;

import java.util.HashMap;
import java.util.List;

public class EvalExpressions implements Transform {
    private HashMap<String, ConstantDefinition> symboltable;

    @Override
    public void apply(AST ast) {
        symboltable = ast.symboltable;

        ast.constantReferences.forEach(this::replaceExpressionInDeclaration);

        symboltable.values().forEach(ast.root::removeChild);
        symboltable.clear();
    }

    private void replaceExpressionInDeclaration(String key, List<Declaration> declarations) {
        declarations.forEach(declaration ->
                declaration.expression = symboltable.get(key).expression);
    }
}
