package core.impl;

import core.Interpolator;
import util.function.ExtendedFunction;
import util.function.interfaces.Dot;

import java.util.Arrays;
import java.util.List;

/**
 * Interpolates dots with Newton's method (calls recurrent function for divided differences)
 * <br/>
 *
 * <img src="https://i.imgur.com/KeRdOUO.png" />
 *
 * @author Arthur Kupriyanov on 21.05.2020
 */
public class NewtonInterpolator implements Interpolator {

    @Override
    public ExtendedFunction interpolate(final List<Dot> dots) {
        List<Dot> points = List.copyOf(dots);

        double[][] dividedDifferences = calculateDividedDifferences( points);

        return new ExtendedFunction(x -> {
            double sum = 0;
            double coef = 1;
            for (int i = dividedDifferences.length - 1; i >= 0; i--) {
                sum += coef * dividedDifferences[i][0];
                coef *= x - points.get(i).getX();
            }

            return sum;
        });
    }

    private double[][] calculateDividedDifferences(List<Dot> points) {
        double[][] cache = createEmptyMatrix(points.size());
        calcDividedDifference(0, 0, cache, points);
        return cache;
    }

    private double calcDividedDifference(int i, int j, double[][] matrix, List<Dot> points){
        if (!Double.isNaN(matrix[i][j])) {
            return matrix[i][j];
        }

        if (j == matrix.length - 1 - i) {
            matrix[i][j] = points.get(i).getY();
            return points.get(i).getY();
        }


        matrix[i][j] = ((calcDividedDifference(i, j + 1, matrix, points) - calcDividedDifference(i + 1, j, matrix, points))
                        / (points.get(i).getX() - points.get(matrix.length - j - 1).getX()) );
        return matrix[i][j];
    }


    private double[][] createEmptyMatrix(int size){
        double[][] matrix = new double[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Double.NaN;
            }
        }

        return matrix;
    }


}
