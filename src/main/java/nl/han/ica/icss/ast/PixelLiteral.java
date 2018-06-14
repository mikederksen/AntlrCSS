package nl.han.ica.icss.ast;

public class PixelLiteral extends Literal {
    public int value;

    public PixelLiteral(int value) {
        this.value = value;
    }

    public PixelLiteral(String text) {
        this.value = Integer.parseInt(text.substring(0, text.length() - 2));
    }

    @Override
    public String getNodeLabel() {
        return "Pixel literal (" + value + ")";
    }

    @Override
    public String toString() {
        return Integer.toString(value) + "px";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof PixelLiteral &&
                ((PixelLiteral) obj).value == value;
    }

    @Override
    public Literal add(Literal other) {
        PixelLiteral otherPx = cast(other, PixelLiteral.class);

        return new PixelLiteral(otherPx.value + value);
    }

    @Override
    public Literal subtract(Literal other) {
        PixelLiteral otherPx = cast(other, PixelLiteral.class);

        return new PixelLiteral(otherPx.value - value);
    }

    @Override
    public Literal multiply(Literal other) {
        ScalarLiteral otherScalar = cast(other, ScalarLiteral.class);

        return new PixelLiteral(otherScalar.value * value);
    }

}
