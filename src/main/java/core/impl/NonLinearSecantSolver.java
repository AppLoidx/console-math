package core.impl;

import core.NonLinearSolver;
import util.function.ExtendedFunction;

/**
 * @author Arthur Kupriyanov on 07.04.2020
 */
public class NonLinearSecantSolver implements NonLinearSolver {

    private final static int MAX_DEPTH = 100_000_000;
    private double lastXVal = Double.NaN;

    @Override
    public double solve(ExtendedFunction extFunction, double accuracy) {

        double currentAccuracy = Double.MAX_VALUE;
        final double leftBoundary = extFunction.getBoundaries()[0];
        final double rightBoundary = extFunction.getBoundaries()[1];
        if (extFunction.apply(leftBoundary) * extFunction.apply(rightBoundary) >= 0) {
            throw new IllegalArgumentException("Функция на концах отрезка должна иметь разные знаки");
        }
        double oldValue = calculateX(extFunction, leftBoundary, rightBoundary);
        double currentValue = Double.NaN;

        int counter = MAX_DEPTH;
        while (currentAccuracy > accuracy) {

            if (isLeftSideDerivative(extFunction, oldValue)) {
                currentValue = calculateLeftSide(extFunction, oldValue);
            } else {
                currentValue = calculateRightSide(extFunction, oldValue);
            }

            currentAccuracy = Math.abs(oldValue - currentValue);
            oldValue = currentValue;

            if (!Double.isNaN(currentValue) && !Double.isInfinite(currentValue)) {
                lastXVal = currentAccuracy;
            } else {
                throw new IllegalArgumentException("Не хватает вычислительной мощности");
            }
            counter--;
            if (counter  < 0 ) {
                throw new IllegalArgumentException("Слишком много итераций (>100_000_000)");
            }
        }

        return currentValue;
    }

    @Override
    public double getLastAnswer() {
        return lastXVal;
    }

    private boolean isLeftSideDerivative(ExtendedFunction function, double oldValue){
        if (function.getBoundaries()[0] < 0 && oldValue >= 0) return true;
        else return function.getBoundaries()[0] >= 0 && oldValue < 0;
    }

    private double calculateRightSide(ExtendedFunction function, double oldValue){
        return calculateX(function, oldValue, function.getBoundaries()[1]);
    }

    private double calculateLeftSide(ExtendedFunction function, double oldValue) {
        return calculateX(function,function.getBoundaries()[0], oldValue);
    }

    private double calculateX(ExtendedFunction func, double a, double b) {
        final double funcA = func.apply(a);
        final double funcB = func.apply(b);
        return (a * funcB - b * funcA) / (funcB - funcA);
    }
}
