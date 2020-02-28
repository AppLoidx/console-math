package model.impl;

import org.junit.jupiter.api.Test;
import testutil.RandomizerUtil;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
class SquareMatrixTest {
    private final RandomizerUtil randomizerUtil = new RandomizerUtil();

    @Test
    public void initTest() {
        SquareMatrix<Integer> actualMatrix = new SquareMatrix<>();
        final int xLen = 12;
        final int yLen = 12;
        Integer[][] matrix = randomizerUtil.getRandomMatrix(Integer.class, xLen, yLen);

        actualMatrix.init(matrix);

        for (int y = 0; y < yLen ; y++){
            for (int x = 0; x < xLen; x++){
                assertEquals(matrix[y][x], actualMatrix.getElement(x, y).get());
            }
        }
    }

    @Test
    public void get_set_Element(){
        SquareMatrix<Long> actualMatrix = new SquareMatrix<>();
        final int xLen = 5;
        final int yLen = 5;
        Long[][] matrix = randomizerUtil.getRandomMatrix(Long.class, xLen, yLen);

        actualMatrix.init(new Long[yLen][xLen]);    // with null values

        for (int y = 0; y < yLen ; y++){
            for (int x = 0; x < xLen; x++){
                Optional<Long> oldValue = actualMatrix.setElement(x, y, matrix[y][x]);
                assertFalse(oldValue.isPresent());
            }
        }

        SquareMatrix<Long> expectedMatrix = new SquareMatrix<>();
        expectedMatrix.init(matrix);

        for (int y = 0; y < yLen ; y++){
            for (int x = 0; x < xLen; x++){
                assertEquals(matrix[y][x], actualMatrix.getElement(x, y).get());
                assertEquals(expectedMatrix.getElement(x, y), actualMatrix.getElement(x, y));
            }
        }
    }

}