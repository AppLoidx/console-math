package util.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Arthur Kupriyanov on 29.03.2020
 */
public class ExtendedFunction implements Function<Double, Double>{
    private Map<Double, Function<Double, Double>> piecewiseMap = new HashMap<>();
    private Function<Double, Double> function;
    private double[] boundaries = new double[2];

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
    }

    public void setSignChange(byte b) {
        signChange = b;
    }
    public double apply(double value) {
        if (piecewiseMap.containsKey(value)) {
            return signChange * piecewiseMap.get(value).apply(value);
        } else {
            return signChange * function.apply(value);
        }
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

    public double[] getBoundaries() {
        return boundaries;
    }


    public DerivativeFunction getDerivativeFunction() {
        return derivativeFunction;
    }

    public void setDerivativeFunction(DerivativeFunction derivativeFunction) {
        this.derivativeFunction = derivativeFunction;
    }

    public static double getMaxValueOfFunc(Function<Double, Double> function, double top, double bottom, double accuracy){
        int sectionsCount = (int) (Math.abs(top - bottom) / accuracy);
        double step = Math.abs((top - bottom) / sectionsCount);
        sectionsCount = sectionsCount - 1;  // don't check top and bottom, because of 0.0000000001d and other shit :)
                                            // machine eps, btw x)
        double currentValue = Math.min(top, bottom);
        double maxVal = -Double.MAX_VALUE;
        System.out.println("cv : " + currentValue);
        while (sectionsCount - 1 > 0) {
            maxVal = Math.max(function.apply(currentValue), maxVal);
            currentValue += step;
            sectionsCount--;
        }
        System.out.println("cv after : " + currentValue);
        System.out.println("max val : " + maxVal);

        // check top and bottom separately
        maxVal = Math.max(maxVal, function.apply(top));
        return maxVal;
    };

    @Override
    public Double apply(Double aDouble) {
        return apply((double)aDouble);
    }


}
