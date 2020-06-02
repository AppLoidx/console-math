package model.impl;

import model.Matrix;

import java.util.Arrays;


/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public class SquareMatrix implements Matrix {

    private float[][] matrix;

    @Override
    public void init(float[][] matrix) {

        if (matrix.length > 0 && matrix.length + 1 == matrix[0].length){
            this.matrix = matrix;
        } else {
            throw new IllegalArgumentException("Provided matrix is not square");
        }

    }

    @Override
    public float setElement(final int y, final int x, final  float value) {
        float oldValue = matrix[y][x];
        matrix[y][x] = value;
        return oldValue;
    }

    @Override
    public float getElement(int  y, int x) {
        return matrix[y][x];
    }

    @Override
    public int getXSize() {
        return matrix[0].length;
    }

    @Override
    public int getYSize() {
        return matrix.length;
    }

    @Override
    public Matrix getClone() {
        Matrix clone = new SquareMatrix();
        float[][] copyMatrix = new float[getYSize()][getXSize()];
        int hIndex = 0;
        for( float[] array : getMatrix() ) {
            copyMatrix[hIndex] = Arrays.copyOf(array, array.length);
            hIndex++;
        }

        clone.init(copyMatrix);

        return clone;
    }

    public float[][] getMatrix(){
        return matrix;
    }

    @Override
    public String toString() {
        return "SquareMatrix{" +
               "matrix=" + Arrays.deepToString(matrix) +
               '}';
    }

    @Override
    public Matrix clone() throws CloneNotSupportedException {
        return (Matrix) super.clone();

    }
}
