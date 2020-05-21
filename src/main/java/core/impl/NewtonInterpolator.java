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
    public ExtendedFunction interpolate(List<Dot> points) {
        int size = points.size();
        double[][] matrix = new double[size][size];
        fillMainLine(matrix, points);
        System.out.println(Arrays.deepToString(matrix));
        for (int i = 1; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                matrix[i][j] = matrix[i - 1][j + 1] - matrix[i - 1][j];
//                dividedDiff[i][j] = dividedDiff[i - 1][j + 1] - dividedDiff[i - 1][j];
            }
        }
        System.out.println(Arrays.deepToString(matrix));
        Dot firstPoint = points.get(0);
        double height = points.get(1).getX() - points.get(0).getX();

        return new ExtendedFunction(x -> {
            double res = firstPoint.getY();
            double q = (x - firstPoint.getX()) / height;
            System.out.println("Q = " + q);
            double product = 1;
            for (int i = 1; i < size; i++) {
                product *= q + 1 - i;
                product /= i;
                res += product * matrix[i][0];
                System.out.println("m = " + matrix[i][0]);
            }
            System.out.println(res);
            return res;
        });
    }

    private void fillMainLine(double[][] matrix, List<Dot> points) {
        for (int i = 0; i < points.size(); i++) {
            matrix[0][i] = points.get(i).getY();
        }
    }
}
