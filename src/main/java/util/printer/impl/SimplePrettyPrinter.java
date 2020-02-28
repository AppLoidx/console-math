package util.printer.impl;

import model.Matrix;
import util.printer.MatrixPrinter;

import java.io.PrintStream;
import java.util.Optional;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public final class SimplePrettyPrinter implements MatrixPrinter {

    public void prettyPrint(Matrix<?> matrix, PrintStream out){
        final String[][] printMap = new String[matrix.getYSize()][matrix.getXSize()];
        final int[] maxLength = {0};

        matrix.consume((y, x) -> {
            Optional<? extends Number> element = matrix.getElement(x, y);
            if (element.isPresent()){
                printMap[y][x] = element.get().toString();
            } else {
                printMap[y][x] = "None";
            }

            maxLength[0] = Math.max(printMap[y][x].length(), maxLength[0]);
        });

        int spaceBetween = maxLength[0] + 1;
        matrix.consume((y, x) -> {
            out.print(String.format("%" + spaceBetween + "s", printMap[y][x]));
        }, out::println);

    }
}
