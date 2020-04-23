package core.impl;

import core.NonLinearSolver;
import util.function.DerivativeFunction;
import util.function.ExtendedFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple-iterative method for solving nonlinear systems
 *
 * @author Arthur Kupriyanov on 23.04.2020
 */
public class NonLinearSystemSolver implements core.NonLinearSystemSolver {

    private static final int MAX_ITERATION = 1_000_000;

    @Override
    public double[] nonlinearSystemSolver(List<ExtendedFunction> extendedFunctions, double accuracy) {
        List<Double> listX = new ArrayList<>();

        for (int i = 0; i < extendedFunctions.size(); i++) {
            listX.add(i, extendedFunctions.get(i).getBoundaries()[i % 2]);
        }

        List<Double> listX0 = new ArrayList<>(listX);
        int counter = 0;
        do {
            for (int i = 0; i < extendedFunctions.size(); i++) {
                ExtendedFunction supportFunc = extendedFunctions.get(i);
                listX0.set(i, listX.get(i));
                if (Math.abs(extendedFunctions.get(0).getDerivativeFunction().apply(listX.get(0))) + Math.abs(extendedFunctions.get(1).getDerivativeFunction().apply(listX.get(1))) > 1) {
                    throw new IllegalArgumentException("Итерационный метод не сходится");
                }
                listX.set(i, supportFunc.apply(listX.get(i)));
                counter++;
            }


        } while(allInListDeltaMoreThanAccuracy(listX, listX0, accuracy) && counter < MAX_ITERATION);

        return new double[]{listX.get(1), listX.get(0)};
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

    private List<ExtendedFunction> createSupportFunction(List<ExtendedFunction> extendedFunctions, double accuracy){
        List<ExtendedFunction> supportFunc = new ArrayList<>();
        for (ExtendedFunction extendedFunction : extendedFunctions) {
            supportFunc.add(NonLinearSolver.createSupportFunction(extendedFunction, accuracy));
        }

        return supportFunc;
    }

    @Override
    public double getLastAnswer() {
        return 0;
    }

}
