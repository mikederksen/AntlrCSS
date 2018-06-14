package nl.han.ica.icss.ast;

public class AddOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Add";
    }

    @Override
    public Literal calculate() {
        final Literal left = getLiteralFromExpression(lhs);
        final Literal right = getLiteralFromExpression(rhs);

        return left.add(right);
    }
}
