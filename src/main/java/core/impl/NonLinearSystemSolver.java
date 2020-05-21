package core.impl;

import core.NonLinearSolver;
import util.function.ExtendedFunction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Simple-iterative method for solving nonlinear systems
 *
 * @author Arthur Kupriyanov on 23.04.2020
 */
public class NonLinearSystemSolver implements core.NonLinearSystemSolver {

    private static final int MAX_ITERATION = 1_000_000;

    @Override
    public double[] nonlinearSystemSolver(List<ExtendedFunction> functions, double accuracy) {
        List<Double> rootList = new ArrayList<>();
        for (int i = 0; i < functions.size(); i++) {
            rootList.add(i, functions.get(i).getBoundaries()[0]); // i % 2
        }

        List<Double> listX0 = new ArrayList<>(rootList);
        int counter = 0;
        do {
            for (int i = 0; i < functions.size(); i++) {
                ExtendedFunction supportFunc = functions.get(i);
                listX0.set(i, rootList.get(i));
                rootList.set(i, supportFunc.apply(rootList.get(i)));
                if (Math.abs(functions.get(0).getDerivativeFunction().apply(rootList.get(0))) > 1 && Math.abs(functions.get(1).getDerivativeFunction().apply(rootList.get(1))) > 1) {
                    throw new IllegalArgumentException("Итерационный метод не сходится т.к производная больше 1");
                }
                for (double d : rootList) if (Double.isInfinite(d)) throw new IllegalArgumentException("Value is infinite");
                counter++;
            }

        } while(allInListDeltaMoreThanAccuracy(rootList, listX0, accuracy) && counter < MAX_ITERATION);

        return new double[]{rootList.get(1), rootList.get(0)};
    }

    private boolean allInListDeltaMoreThanAccuracy(List<Double> listX, List<Double> listX0, double accuracy){

        boolean moreThanAccuracy = false;
        for (int i = 0; i < listX.size(); i++) {
            double x = listX.get(i);
            double x0 = listX0.get(i);

            moreThanAccuracy = Math.abs(x - x0) >= accuracy;
        }

        return moreThanAccuracy;
    }

    @Override
    public double getLastAnswer() {
        return 0;
    }

}
