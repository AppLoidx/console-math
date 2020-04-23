package util.function;

import util.function.interfaces.DoubleFunction;
import util.function.interfaces.FourFunction;
import util.function.interfaces.TripleFunction;

import java.util.function.Function;

/**
 * @author Arthur Kupriyanov on 23.04.2020
 */
public class SystemFunction {
    private DoubleFunction doubleFunction;
    private TripleFunction tripleFunction;
    private FourFunction fourFunction;

    public double apply(double x1, double x2) {
        if (doubleFunction == null) throw new IllegalArgumentException("System function don't have double function");
        return doubleFunction.apply(x1, x2);
    }
    public double apply(double x1, double x2, double x3) {
        if (tripleFunction == null) throw new IllegalArgumentException("System function don't have triple function");

        return tripleFunction.apply(x1, x2, x3);
    }
    public double apply(double x1, double x2, double x3, double x4) {
        if (fourFunction == null) throw new IllegalArgumentException("System function don't have fourth function");

        return fourFunction.apply(x1, x2, x3, x4);
    }

    public SystemFunction(DoubleFunction doubleFunction){

        this.doubleFunction = doubleFunction;
    }

    public SystemFunction(TripleFunction tripleFunction) {
        this.tripleFunction = tripleFunction;
    }

    public SystemFunction(FourFunction fourFunction) {
        this.fourFunction = fourFunction;
    }
}
