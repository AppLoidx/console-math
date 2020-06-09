package util.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Arthur Kupriyanov on 29.03.2020
 */
public class ExtendedFunction implements Function<Double, Double> {
    private Map<Double, Function<Double, Double>> piecewiseMap = new HashMap<>();
    private final Function<Double, Double> function;

    private ExtendedFunction representation;
    private double[] boundaries = new double[2];

    public Function<Double, Boolean> getIsInRange() {
        return isInRange;
    }

    public void setIsInRange(Function<Double, Boolean> isInRange) {
        this.isInRange = isInRange;
    }

    private Function<Double, Boolean> isInRange = x -> true;

    private DerivativeFunction derivativeFunction;
    private byte signChange = 1;

    public ExtendedFunction(Function<Double, Double> function) {
        this.function = function;
    }

    public ExtendedFunction(ExtendedFunction extFunc) {
        piecewiseMap = new HashMap<>(extFunc.piecewiseMap);
        double bottom = extFunc.boundaries[0];
        double top = extFunc.boundaries[1];
        boundaries = new double[]{bottom, top};

        function = extFunc.function;
        derivativeFunction = extFunc.derivativeFunction;
        signChange = extFunc.signChange;
        this.representation = getRepresentation();
    }


    public ExtendedFunction getRepresentation() {
        return representation;
    }

    public void setRepresentation(ExtendedFunction representation) {
        this.representation = representation;
    }

    public void setSignChange(byte b) {
        signChange = b;
    }

    public double apply(double value) {
        if (isInRange.apply(value)) return signChange * piecewiseMap.getOrDefault(value, function).apply(value);
        else throw new IllegalArgumentException("Значение функции вышло из области допустимых значений");
    }

    public void setPiecewiseMap(Map<Double, Function<Double, Double>> piecewiseMap) {
        this.piecewiseMap = piecewiseMap;
    }

    public void setBoundaries(double bottom, double top) {
        boundaries[0] = Math.min(bottom, top);
        boundaries[1] = Math.max(bottom, top);

        if (bottom > top) {
            signChange = -1;
        } else {
            signChange = 1;
        }

    }

    public void setBoundaries(double[] boundaries) {
        if (boundaries.length != 2) {
            throw new IllegalArgumentException("Invalid size of boundaries " + Arrays.toString(boundaries));
        }
        setBoundaries(boundaries[0], boundaries[1]);
    }

    public double[] getBoundaries() {
        return boundaries;
    }


    public DerivativeFunction getDerivativeFunction() {
        return derivativeFunction;
    }

    public void setDerivativeFunction(DerivativeFunction derivativeFunction) {
        this.derivativeFunction = derivativeFunction;
    }

    public static double getMaxValueOfFunc(Function<Double, Double> function, double top, double bottom, double accuracy) {
        int sectionsCount = (int) (Math.abs(top - bottom) / accuracy);
        double step = Math.abs((top - bottom) / sectionsCount);
        sectionsCount = sectionsCount - 1;  // don't check top and bottom, because of 0.0000000001d and other shit :)
        // machine eps, btw x)
        double currentValue = Math.min(top, bottom);
        double maxVal = -Double.MAX_VALUE;
        while (sectionsCount - 1 > 0) {
            maxVal = Math.max(function.apply(currentValue), maxVal);
            currentValue += step;
            sectionsCount--;
        }

        // check top and bottom separately
        maxVal = Math.max(maxVal, function.apply(top));
        return maxVal;
    }

    ;

    @Override
    public Double apply(Double aDouble) {
        return apply((double) aDouble);
    }


}
