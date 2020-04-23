package util.function;

import java.util.function.Function;

/**
 * @author Arthur Kupriyanov on 07.04.2020
 */
public class DerivativeFunction implements Function<Double, Double>{
    private Function<Double, Double> function;
    private Function<Double, Boolean> isInRange = x -> true;

    public DerivativeFunction(Function<Double, Double> function) {
        this.function = function;
    }

    public double apply(double x ){
        if (isInRange.apply(x)) {
            return function.apply(x);
        } else {
            throw new IllegalArgumentException("Derivative out of range");
        }
    }


    public void setIsInRange(Function<Double, Boolean> isInRange) {
        this.isInRange = isInRange;
    }

    @Override
    public Double apply(Double aDouble) {
        return apply((double) aDouble);
    }
}
