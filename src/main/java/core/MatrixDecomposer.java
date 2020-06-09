package core;

import model.Matrix;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
public interface MatrixDecomposer {
    /**
     * Decompose matrix like transform to pyramid matrix
     *
     * @param matrix source matrix
     * @return transformed matrix. It doesn't might be clone
     */
    Matrix decompose(Matrix matrix);

    /**
     * Get determinant calculated within process
     *
     * @return value of determinant
     */
    float getDeterminant();
}
