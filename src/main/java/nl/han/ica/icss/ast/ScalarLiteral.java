package nl.han.ica.icss.ast;

public class ScalarLiteral extends Literal {
    public int value;

    public ScalarLiteral(int value) {
        this.value = value;
    }

    public ScalarLiteral(String text) {
        this.value = Integer.parseInt(text);
    }

    @Override
    public String getNodeLabel() {
        return "Scalar literal (" + value + ")";
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ScalarLiteral &&
                ((ScalarLiteral) obj).value == value;
    }
}
