package core.impl;

import core.IntegralSolver;
import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 18.03.2020
 */
public class SimpsonSolver implements IntegralSolver {
    @Override
    public double solve(ExtendedFunction extendedFunction, double top, double bottom, double partition) {
        double[] boundaries = new double[2];
        addBoundariesTo(boundaries, top, bottom);

        double step = (boundaries[1] - boundaries[0] * 1d) / partition;

        double sum1 = 0;
        double sum2 = 0;

        for (int i = 0; i < partition; i++) {
            double functionValue = extendedFunction.apply(boundaries[0] + i * step);
            if (i % 2 == 0) {
                sum1 = sum1 + functionValue;
            } else {
                sum2 = sum2 + functionValue;
            }
        }
        byte sign = (byte) (top > bottom ? 1 : -1);

        return sign * step / 3 * (extendedFunction.apply(boundaries[0]) + extendedFunction.apply(boundaries[1]) + 2 * sum1 + 4 * sum2);
    }

    private void addBoundariesTo(double[] boundaries, double top, double bottom) {
        boundaries[0] = Math.min(top, bottom);
        boundaries[1] = Math.max(top, bottom);
    }

}
