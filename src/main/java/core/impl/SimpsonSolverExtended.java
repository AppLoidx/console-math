package core.impl;

import core.IntegralSolver;
import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 18.03.2020
 */
public class SimpsonSolverExtended implements IntegralSolver {
    private final SimpsonSolver SOLVER = new SimpsonSolver();

    private volatile Double lastAccuracy;
    private volatile Double lastPartition;
    private double limit = 1_000_000;

    private Double lastNormalValue;

    public double solveWithAccuracy(ExtendedFunction extendedFunction, double top, double bottom, double expectedAccuracy) {
        double partition = 40;
        double actualAccuracy = Double.MAX_VALUE;
        double oldValue = solve(extendedFunction, top, bottom, partition);
        while (actualAccuracy > expectedAccuracy) {

            partition = partition * 2;
            double actualValue = solve(extendedFunction, top, bottom, partition);
            actualAccuracy = Math.abs(actualValue - oldValue) / 15; // 2^4 - 1
            oldValue = actualValue;
            if (!Double.isNaN(oldValue) && !Double.isInfinite(oldValue)) {
                lastNormalValue = oldValue;
            }

            if (partition > limit || partition < 0) {
                throw new IllegalArgumentException("Partition overflow");
            }
            lastAccuracy = actualAccuracy;
        }

        lastPartition = partition;

        return oldValue;
    }

    @Override
    public double solve(ExtendedFunction function, double top, double bottom, double partition) {
        return SOLVER.solve(function, top, bottom, partition);
    }

    public Double getLastAccuracy() {
        return lastAccuracy;
    }

    public Double getLastPartition() {
        return lastPartition;
    }

    public Double getLastNormalValue() {
        return lastNormalValue;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }
}
