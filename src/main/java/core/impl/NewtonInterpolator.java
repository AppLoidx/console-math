package core.impl;

import core.Interpolator;
import util.function.ExtendedFunction;
import util.function.interfaces.Dot;

import java.util.Arrays;
import java.util.List;

/**
 * @author Arthur Kupriyanov on 21.05.2020
 */
public class NewtonInterpolator implements Interpolator {
    @Override
    public ExtendedFunction interpolate(final List<Dot> points) {
        int size = points.size();
        double[][] matrix = new double[size][size];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = Double.NaN;
            }
        }

        fillMatrix(matrix, points);

        return new ExtendedFunction(x -> {
            double sum = 0;
            double coef = 1;
            for (int i = matrix.length - 1; i > 0; i--) {
                sum += coef * matrix[i][0];
                coef *= x - points.get(i).getX();
            }

            return sum;
        });
    }

    private void fillMatrix(double[][] matrix, List<Dot> points) {
        calcValue(0, 0, matrix, points);
    }

    private double calcValue(int i, int j, double[][] matrix, List<Dot> points){
        if (j == matrix.length - 1 - i) {
            matrix[i][j] = points.get(i).getY();
            return points.get(i).getY();
        }
        if (!Double.isNaN(matrix[i][j])) return matrix[i][j];

        matrix[i][j] = ((calcValue(i, j + 1, matrix, points) - calcValue(i + 1, j, matrix, points)) / (points.get(i).getX() - points.get(matrix.length - j - 1).getX()) );
        return matrix[i][j];
    }


}
