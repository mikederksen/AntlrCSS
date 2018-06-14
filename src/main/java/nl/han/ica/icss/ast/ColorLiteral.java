package nl.han.ica.icss.ast;

import java.awt.*;

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
        ColorLiteral otherColorLiteral = cast(other, ColorLiteral.class);

        Color thisColor = Color.decode("#" + value);
        Color otherColor = Color.decode("#" + otherColorLiteral.value);

        int newR = thisColor.getRed() + otherColor.getRed();
        int newG = thisColor.getGreen() + otherColor.getGreen();
        int newB = thisColor.getBlue() + otherColor.getBlue();

        Color newColor = new Color(
                newR <= 255 ? newR : 255,
                newG <= 255 ? newG : 255,
                newB <= 255 ? newB : 255);

        String newHex = Integer.toHexString(newColor.getRGB()).substring(2);

        return new ColorLiteral(newHex);
    }

    @Override
    public Literal subtract(Literal other) {
        ColorLiteral otherColorLiteral = cast(other, ColorLiteral.class);

        Color thisColor = Color.decode("#" + value);
        Color otherColor = Color.decode("#" + otherColorLiteral.value);

        int newR = thisColor.getRed() - otherColor.getRed();
        int newG = thisColor.getGreen() - otherColor.getGreen();
        int newB = thisColor.getBlue() - otherColor.getBlue();

        Color newColor = new Color(
                newR >= 0 ? newR : 0,
                newG >= 0 ? newG : 0,
                newB >= 0 ? newB : 0);

        String newHex = Integer.toHexString(newColor.getRGB()).substring(2);

        return new ColorLiteral(newHex);
    }

    @Override
    public Literal multiply(Literal other) {
        ScalarLiteral otherScalar = cast(other, ScalarLiteral.class);

        Color thisColor = Color.decode("#" + value);

        int newR = thisColor.getRed() * otherScalar.value;
        int newG = thisColor.getGreen() * otherScalar.value;
        int newB = thisColor.getBlue() * otherScalar.value;

        Color newColor = new Color(
                newR <= 255 ? newR : 255,
                newG <= 255 ? newG : 255,
                newB <= 255 ? newB : 255);

        String newHex = Integer.toHexString(newColor.getRGB()).substring(2);

        return new ColorLiteral(newHex);
    }
}
