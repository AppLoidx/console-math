package model;

import java.io.InputStream;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.DoubleConsumer;
import java.util.stream.IntStream;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public interface Matrix<T extends Number> {
    /**
     * Initialize matrix
     *
     * @param matrix array matrix input
     */
    void init(T[][] matrix);

    /**
     * @param x     x-coordinate from left-to-right
     * @param y     y-coordinate from top-to-bottom
     * @param value value of cell
     * @return old cell value
     */
    Optional<T> setElement(int x, int y, T value);

    /**
     * @param x x-coordinate from left-to-right
     * @param y y-coordinate from top-to-bottom
     * @return cell value
     */
    Optional<T> getElement(int x, int y);


    default void consume(BiConsumer<Integer, Integer> biConsumer, Runnable afterEachRowCallback) {
        IntStream.range(0, getYSize())
                .forEach(y -> {
                    IntStream.range(0, getXSize())
                            .forEach(x -> biConsumer.accept(y, x));
                    afterEachRowCallback.run();
                });
    }

    default void consume(BiConsumer<Integer, Integer> biConsumer) {
        consume(biConsumer, () -> {});
    }

    int getXSize();

    int getYSize();

}
