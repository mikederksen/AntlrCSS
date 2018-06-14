package nl.han.ica.icss.ast;

public abstract class Literal extends Expression {
    public abstract Literal add(Literal other);

    public abstract Literal subtract(Literal other);

    public abstract Literal multiply(Literal other);

    protected <T extends Literal> T cast(Literal literal, Class<T> requiredClass) {
        if (literal.getClass().isAssignableFrom(requiredClass)) {
            return ((T) literal);
        } else {
            throw new UnsupportedOperationException();
        }
    }
}

