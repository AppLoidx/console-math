package util.function;

import util.function.interfaces.Dot;

import java.text.SimpleDateFormat;

/**
 * @author Arthur Kupriyanov on 21.05.2020
 */
public class SimpleDot implements Dot {
    private final double x;
    private final double y;
    public SimpleDot(double x , double y) {
        this.x = x;
        this.y = y;
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
}
