package model;


import core.MatrixDecomposer;

import java.util.function.BiConsumer;
import java.util.stream.IntStream;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public interface Matrix {
    /**
     * Initialize matrix
     *
     * @param matrix array matrix input
     */
    void init(float[][] matrix);

    /**
     * @param x     x-coordinate from left-to-right
     * @param y     y-coordinate from top-to-bottom
     * @param value value of cell
     * @return old cell value
     */
    float setElement(int y, int x, float value);

    /**
     * @param x x-coordinate from left-to-right
     * @param y y-coordinate from top-to-bottom
     * @return cell value
     */
    float getElement(int y, int x);


    default void consume(BiConsumer<Integer, Integer> biConsumer, Runnable afterEachRowCallback) {
        IntStream.range(0, getYSize())
                .forEach(y -> {
                    IntStream.range(0, getXSize())
                            .forEach(x -> biConsumer.accept(y, x));
                    afterEachRowCallback.run();
                });
    }

    default void consumeReverse(BiConsumer<Integer, Integer> biConsumer, Runnable afterEachRowCallback) {
        IntStream.range(getYSize(), 0)
                .forEach(y -> {
                    IntStream.range(getXSize(), 0)
                            .forEach(x -> biConsumer.accept(y, x));
                    afterEachRowCallback.run();
                });
    }


    default void consume(BiConsumer<Integer, Integer> biConsumer) {
        consume(biConsumer, () -> {
        });
    }

    default void consumeReverse(BiConsumer<Integer, Integer> biConsumer) {
        consumeReverse(biConsumer, () -> {
        });
    }

    default Matrix decompose(MatrixDecomposer matrixDecomposer) {
        return matrixDecomposer.decompose(this);
    }

    int getXSize();

    int getYSize();

    Matrix getClone();

}
