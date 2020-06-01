package util.function;

import java.util.function.BiFunction;

/**
 * @author Arthur Kupriyanov on 01.06.2020
 */
public class DiffEquation implements BiFunction<Double, Double, Double>{
    private final BiFunction<Double, Double, Double> biFunction;

    public DiffEquation(BiFunction<Double, Double, Double> biFunction) {
        this.biFunction = biFunction;
    }

    @Override
    public Double apply(Double x, Double y) {
        return this.biFunction.apply(x, y);
    }
}
