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
        this.matrix = matrix;
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

    public float[][] getMatrix(){
        return matrix;
    }

    @Override
    public String toString() {
        return "SquareMatrix{" +
               "matrix=" + Arrays.toString(matrix) +
               '}';
    }
}
