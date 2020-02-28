package core.impl;

import core.MatrixSolver;
import model.Matrix;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public class GaussMatrixSolver<T extends Number> implements MatrixSolver<T> {

    @Override
    public float getDeterminant(Matrix<T> matrix) {
        return 0;
    }
}
