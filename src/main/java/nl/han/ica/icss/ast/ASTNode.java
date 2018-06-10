package nl.han.ica.icss.ast;

import nl.han.ica.icss.checker.SemanticError;

import java.util.ArrayList;
import java.util.List;

public class ASTNode {

    private SemanticError error = null;

    public String getNodeLabel() {
        return "ASTNode";
    }

    public List<ASTNode> getChildren() {
        return new ArrayList<>();
    }

    public void addChild(ASTNode child) {
    }

    public void removeChild(ASTNode child) {
    }

    public SemanticError getError() {
        return this.error;
    }

    public void setError(String description) {
        this.error = new SemanticError(description);
    }

    public boolean hasError() {
        return error != null;
    }
}
