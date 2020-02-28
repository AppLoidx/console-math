import model.impl.SquareMatrix;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public class Math {
    public static void main(String[] args) {
        SquareMatrix<Integer> matrix = new SquareMatrix<>();
        Integer[][] matrixInit = new Integer[][]{{1, 2}, {3, null}};
        matrix.init(matrixInit);
        System.out.println(matrix.getElement(0, 0).isPresent());
        System.out.println(matrix.getElement(0, 1).isPresent());
        System.out.println(matrix.getElement(1, 0).isPresent());
        System.out.println(matrix.getElement(1, 1).isPresent());
    }
}
