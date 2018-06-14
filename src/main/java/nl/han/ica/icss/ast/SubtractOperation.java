package nl.han.ica.icss.ast;

public class SubtractOperation extends Operation {

    @Override
    public String getNodeLabel() {
        return "Subtract";
    }

    @Override
    public Literal calculate() {
        final Literal left = getLiteralFromExpression(lhs);
        final Literal right = getLiteralFromExpression(rhs);

        return left.subtract(right);
    }
}
