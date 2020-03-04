package core.impl;

import core.MatrixSolver;
import core.ResidualColumn;
import model.Matrix;


/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public class GaussMatrixSolver implements MatrixSolver {

    private static final PyramidDecomposer PYRAMID_DECOMPOSER = new PyramidDecomposer();


    private float determinant;
    private float[] variables;
    private float[] residualColumn;
    private Matrix triangleMatrix;
    private Matrix matrix;


    public GaussMatrixSolver(Matrix matrix){
        this.matrix = matrix.getClone();
        this.variables = solve();
    }


    @Override
    public float[] solve() {

        this.triangleMatrix = matrix.decompose(PYRAMID_DECOMPOSER);

        this.determinant = PYRAMID_DECOMPOSER.getDeterminant();

        return backSubstitution(matrix);
    }

    private float[] backSubstitution(Matrix matrix){
        float[] values = new float[matrix.getYSize()];
        for (int hIndex = matrix.getYSize() - 1; hIndex >= 0; hIndex--) {

            float rightValue = matrix.getElement(hIndex, matrix.getXSize() - 1);

            values[hIndex] = findVariable(matrix, hIndex, rightValue, values);
        }

        return values;
    }

    @Override
    public float getDeterminant() {
        return this.determinant;
    }

    public float[] getVariables(){
        return this.variables;
    }

    public Matrix getTriangleMatrix(){
        return triangleMatrix;
    }

    public float[] getResidualColumn(){
        return residualColumn = ResidualColumn.getFrom(matrix, getVariables());
    }

    private float findVariable(Matrix matrix, int hIndex, float initialValue, float[] previousVariables){
        float value = initialValue;
        for (int vIndex = matrix.getXSize() - 2; vIndex >= 0; vIndex--) {
            value = value - matrix.getElement(hIndex, vIndex) * previousVariables[vIndex];
        }
        return value;
    }

    private float[] reverse(float[] source){
        float[] reversed = new float[source.length];
        int index = reversed.length - 1;
        for (float val : source){
            reversed[index] = val;
            index--;
        }

        return reversed;
    }
}
