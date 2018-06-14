package nl.han.ica.icss.ast;

public class ColorLiteral extends Literal {
    public String value;

    public ColorLiteral(String value) {
        this.value = value;
    }

    @Override
    public String getNodeLabel() {
        return "Color literal (" + value + ")";
    }

    @Override
    public String toString() {
        return "#" + value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ColorLiteral &&
               ((ColorLiteral) obj).value.equals(value);
    }

    @Override
    public Literal add(Literal other) {
        if (other instanceof ColorLiteral) {
           throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Literal subtract(Literal other) {
        if (other instanceof ColorLiteral) {
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Literal multiply(Literal other) {
        if (other instanceof ColorLiteral) {
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
