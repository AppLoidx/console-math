package testutil;

import java.util.Random;
import java.util.stream.IntStream;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public final class RandomizerUtil {

    private static final Random random = new Random();

    @SuppressWarnings("unchecked")
    public <T>  T getRandomValue  (Class<T> clazz){
        if (clazz.equals(Integer.class)){
            return (T) getRandomInt();
        } else if (clazz.equals(Float.class)){
            return (T) getRandomFloat();
        } else if (clazz.equals(Long.class)){
            return (T) getRandomLong();
        }
        throw new IllegalArgumentException("Type not found");
    }

    @SuppressWarnings("unchecked")
    public <T> T[][] getRandomMatrix(Class<T> clazz, int x, int y){
           if (clazz.equals(Integer.class)){
               return (T[][]) getRandomIntMatrix(x, y);
           }

           if (clazz.equals(Float.class)){
               return (T[][]) getRandomFloatMatrix(x, y);
           }

           if (clazz.equals(Long.class)){
               return (T[][]) getRandomLongMatrix(x, y);
           }

           throw new IllegalArgumentException("Type not found");
    }

    private Integer[][] getRandomIntMatrix(int x, int y){
        Integer[][] matrix = new Integer[y][x];
        return matrixFill(matrix, Integer.class, x, y);
    }

    private Float[][] getRandomFloatMatrix(int x, int y){
        Float[][] matrix = new Float[y][x];
        return matrixFill(matrix, Float.class, x, y);
    }

    private Long[][] getRandomLongMatrix(int x, int y){
        Long[][] matrix = new Long[y][x];
        return matrixFill(matrix, Long.class, x, y);
    }

    private <T> T[][] matrixFill(final T[][] matrix, Class<T> clazz, int x, int y){
        IntStream.range(0, y)
                .forEach(i -> {
                    IntStream.range(0, x)
                            .forEach(j -> {
                                matrix[i][j] = getRandomValue(clazz);
                            });
                });

        return matrix;
    }

    private static Long getRandomLong(){
        return random.nextLong();
    }

    private static Float getRandomFloat(){
        return random.nextFloat();
    }

    private static Integer getRandomInt(){
        return random.nextInt();
    }
}
