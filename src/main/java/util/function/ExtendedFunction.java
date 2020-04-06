package util.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Arthur Kupriyanov on 29.03.2020
 */
public class ExtendedFunction {
    private Map<Double, Function<Double, Double>> piecewiseMap = new HashMap<>();
    private Function<Double, Double> function;

    public ExtendedFunction(Function<Double, Double> function) {
        this.function = function;
    }

    public double apply(double value) {
        if (piecewiseMap.containsKey(value)) {
            return piecewiseMap.get(value).apply(value);
        } else {
            return function.apply(value);
        }
    }

    public void setPiecewiseMap(Map<Double, Function<Double, Double>> piecewiseMap) {
        this.piecewiseMap = piecewiseMap;
    }


}
