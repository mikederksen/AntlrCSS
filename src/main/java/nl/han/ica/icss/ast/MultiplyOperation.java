package nl.han.ica.icss.ast;

public class MultiplyOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Multiply";
    }

    @Override
    public Literal calculate() {
        final Literal left = getLiteralFromExpression(lhs);
        final Literal right = getLiteralFromExpression(rhs);

        return left.multiply(right);
    }
}
