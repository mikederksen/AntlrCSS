package nl.han.ica.icss.ast;

import java.util.ArrayList;

public class Stylerule extends ASTNode {

    public Selector selector;
    public ArrayList<ASTNode> body = new ArrayList<>();

    public Stylerule() {
    }

    public Stylerule(Selector selector, ArrayList<ASTNode> body) {
        this.selector = selector;
        this.body = body;
    }

    @Override
    public String getNodeLabel() {
        return "Stylerule";
    }

    @Override
    public ArrayList<ASTNode> getChildren() {
        ArrayList<ASTNode> children = new ArrayList<>();
        if (selector != null)
            children.add(selector);
        children.addAll(body);

        return children;
    }

    @Override
    public void addChild(ASTNode child) {
        if (child instanceof Selector)
            selector = (Selector) child;
        else
            body.add(child);
    }

    @Override
    public void removeChild(ASTNode child) {
        if (selector == child)
            selector = null;
        else
            body.remove(child);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(selector);
        stringBuilder.append(" {\n");

        body.forEach(c -> appendChild(stringBuilder, c));

        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }

    private void appendChild(StringBuilder stringBuilder, ASTNode child) {
        stringBuilder.append("\t");
        stringBuilder.append(child);
        stringBuilder.append("\n");
    }
}
