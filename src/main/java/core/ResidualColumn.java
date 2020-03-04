package core;

import model.Matrix;

/**
 * @author Arthur Kupriyanov on 05.03.2020
 */
public final class ResidualColumn {
    private ResidualColumn(){}

    public static float[] getFrom(Matrix matrix , float[] variables){
        float[] residualColumn = new float[matrix.getYSize()];
        for (int hIndex = 0; hIndex < matrix.getYSize(); hIndex++) {
            float sum = 0;
            for (int vIndex = 0; vIndex < matrix.getXSize() - 1; vIndex++) {    // without B column
                sum = sum + matrix.getElement(hIndex, vIndex) * variables[vIndex];
            }
            residualColumn[hIndex] = Math.abs(sum - matrix.getElement(hIndex, matrix.getXSize() - 1));
        }

        return residualColumn;
    }
}
