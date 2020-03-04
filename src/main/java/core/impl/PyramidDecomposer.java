package core.impl;

import core.MatrixDecomposer;
import model.Matrix;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
public class PyramidDecomposer implements MatrixDecomposer {

    @Override
    public Matrix decompose(Matrix matrix) {
            for (int y = 0; y < matrix.getYSize(); y++){
                float[] equation = new float[matrix.getXSize()];
                float leadElement = matrix.getElement(y, y);
                for (int x = 0 ; x < matrix.getXSize()  ; x++){
                    equation[x] = matrix.getElement(y, x) / leadElement;
                    matrix.setElement(y, x, equation[x]);
                }
                for (int z = y + 1; z < matrix.getYSize(); z++) {
                    float nextLeadElem = matrix.getElement(z, y);
                    for (int x = 0; x < matrix.getXSize(); x++) {
                        matrix.setElement(z, x, matrix.getElement(z, x) - nextLeadElem * equation[x]);
                    }
                }
            }

            return matrix;
    }

}
