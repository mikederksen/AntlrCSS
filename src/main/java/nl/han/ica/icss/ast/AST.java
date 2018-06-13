package nl.han.ica.icss.ast;

import nl.han.ica.icss.checker.SemanticError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AST {
    //The root of the tree
    public Stylesheet root;

    //The symbol table.
    //We only need to track assignments in the symbol table.
    public HashMap<String, ConstantDefinition> symboltable;

    public HashMap<String, List<Declaration>> constantReferences;

    //Keep track of whether this AST was typechecked
    public boolean checked;

    public AST() {
        root = new Stylesheet();
        symboltable = new HashMap<>();
        checked = false;
    }

    public AST(Stylesheet root) {
        super();
        this.root = root;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setRoot(Stylesheet stylesheet) {
        root = stylesheet;
    }

    public List<SemanticError> getErrors() {
        List<SemanticError> errors = new ArrayList<>();
        collectErrors(errors, root);

        return errors;
    }

    private void collectErrors(List<SemanticError> errors, ASTNode node) {
        if (node.hasError()) {
            errors.add(node.getError());
        }

        for (ASTNode child : node.getChildren()) {
            collectErrors(errors, child);
        }
    }

    @Override
    public String toString() {
        return root.toString();
    }
}
