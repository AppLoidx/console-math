package testutil;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public final class RandomizerUtil {

    private static final Random random = new Random();


    public static float[][] getRandomMatrix(int x, int y) {
        return getRandomFloatMatrix(x, y);
    }


    private static float[][] getRandomFloatMatrix(int x, int y) {
        float[][] matrix = new float[y][x];
        return matrixFill(matrix, x, y);
    }


    private static float[][] matrixFill(final float[][] matrix, int x, int y) {
        IntStream.range(0, y)
                .forEach(i -> {
                    IntStream.range(0, x)
                            .forEach(j -> {
                                matrix[i][j] = Math.round(getRandomFloat());
                            });
                });

        return matrix;
    }

    private static float getRandomFloat() {
        final int min = 12;
        final int max = 20;
        return min + random.nextFloat() * (max - min);
    }

}
