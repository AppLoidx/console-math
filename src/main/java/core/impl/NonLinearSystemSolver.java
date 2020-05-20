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
        List<Double> listX = new ArrayList<>();

        for (int i = 0; i < functions.size(); i++) {
            listX.add(i, functions.get(i).getBoundaries()[i % 2]);
        }

//        functions = functions.stream()
//                .map(f -> NonLinearSolver.createSupportFunction(f, accuracy))
//                .collect(Collectors.toList());

        List<Double> listX0 = new ArrayList<>(listX);
        int counter = 0;
        do {
            for (int i = 0; i < functions.size(); i++) {
                ExtendedFunction supportFunc = functions.get(i);
                listX0.set(i, listX.get(i));
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

    @Override
    public double getLastAnswer() {
        return 0;
    }

}
