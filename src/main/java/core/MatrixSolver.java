package core;

import model.Matrix;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public interface MatrixSolver<T extends Number> {

    float getDeterminant(Matrix<T> matrix);

}
