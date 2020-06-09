package util.function;

import util.function.interfaces.Dot;

import java.awt.*;

/**
 * @author Arthur Kupriyanov on 21.05.2020
 */
public class SimpleDot implements Dot {
    private final double x;
    private final double y;
    private Color color;

    public SimpleDot(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public SimpleDot(double x, double y, Color color) {
        this(x, y);
        this.color = color;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public String toString() {
        return "SimpleDot{" +
               "x=" + x +
               ", y=" + y +
               '}';
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
