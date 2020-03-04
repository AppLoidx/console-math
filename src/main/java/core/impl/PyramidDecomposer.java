package core.impl;

import core.MatrixDecomposer;
import model.Matrix;

/**
 * @author Arthur Kupriyanov on 03.03.2020
 */
public class PyramidDecomposer implements MatrixDecomposer {

    private float determinant;
    private boolean isDeterminantSet;

    @Override
    public Matrix decompose(Matrix matrix) {
        float determinant = 1;
        for (int vIndex = 0; vIndex < matrix.getYSize(); vIndex++) {
            float leadElement = matrix.getElement(vIndex, vIndex);
            determinant = determinant * leadElement;
            float[] equation = makeEquation(matrix, leadElement, vIndex);
            bottomSubtract(matrix, vIndex, equation);
        }

        this.determinant = determinant;
        isDeterminantSet = true;

        return matrix;
    }

    @Override
    public float getDeterminant() {
        if (isDeterminantSet)
            return determinant;
        else
            throw new RuntimeException("Determinant is not set!");
    }

    private float[] makeEquation(Matrix matrix, float leadElement, int vIndex) {
        float[] equation = new float[matrix.getXSize()];

        for (int hIndex = 0; hIndex < matrix.getXSize(); hIndex++) {
            equation[hIndex] = matrix.getElement(vIndex, hIndex) / leadElement;
            matrix.setElement(vIndex, hIndex, equation[hIndex]);
        }

        return equation;
    }

    private void bottomSubtract(Matrix matrix, int vIndex, float[] equation) {
        for (int z = vIndex + 1; z < matrix.getYSize(); z++) {
            float nextLeadElem = matrix.getElement(z, vIndex);
            for (int hIndex = 0; hIndex < matrix.getXSize(); hIndex++) {
                matrix.setElement(z, hIndex, matrix.getElement(z, hIndex) - nextLeadElem * equation[hIndex]);
            }
        }
    }

}
