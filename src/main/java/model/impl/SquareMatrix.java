package model.impl;

import model.Matrix;

import java.util.Optional;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public class SquareMatrix<T extends Number> implements Matrix<T> {

    private T[][] matrix;

    @Override
    public void init(T[][] matrix) {
        this.matrix = matrix;
    }

    @Override
    public Optional<T> setElement(final int x, final  int y, final  T value) {
        Optional<T> oldValue = Optional.ofNullable(matrix[y][x]);
        matrix[y][x] = value;
        return oldValue;
    }

    @Override
    public Optional<T> getElement(int x, int y) {
        T element = matrix[y][x];
        return Optional.ofNullable(element);
    }

    @Override
    public int getXSize() {
        return matrix[0].length;
    }

    @Override
    public int getYSize() {
        return matrix.length;
    }
}
