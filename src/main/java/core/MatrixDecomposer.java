package core;

import model.Matrix;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
@FunctionalInterface
public interface MatrixDecomposer {
    Matrix decompose(Matrix matrix);
}
