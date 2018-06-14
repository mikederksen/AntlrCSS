package nl.han.ica.icss.ast;

import java.util.ArrayList;

public abstract class Operation extends Expression {

    public Expression lhs;
    public Expression rhs;

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        children.add(lhs);
        children.add(rhs);
        return children;
    }

    @Override
    public void addChild(ASTNode child) {
        if(lhs == null) {
            lhs = (Expression) child;
        } else if(rhs == null) {
            rhs = (Expression) child;
        }
    }

    @Override
    public void removeChild(ASTNode child) {
        if(lhs == child) {
            lhs = null;
        } else if(rhs == child) {
            rhs = null;
        }
    }
}
