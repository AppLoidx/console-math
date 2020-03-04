package util.printer.impl;

import model.Matrix;
import util.printer.MatrixPrinter;

import java.io.PrintStream;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public final class SimplePrettyPrinter implements MatrixPrinter {

    private PrintStream out = System.out;

    public SimplePrettyPrinter(){}
    public SimplePrettyPrinter(PrintStream out){
        this.out = out;
    }


    @Override
    public void prettyPrint(Matrix matrix, PrintStream out){
        final String[][] printMap = new String[matrix.getYSize()][matrix.getXSize()];
        final int[] maxLength = {0};

        matrix.consume((y, x) -> {
            float element = matrix.getElement(y, x);
            printMap[y][x] = String.valueOf(element);

            maxLength[0] = Math.max(printMap[y][x].length(), maxLength[0]);
        });

        int spaceBetween = maxLength[0] + 1;
        matrix.consume((y, x) -> {
            out.print(String.format("%" + spaceBetween + "s", printMap[y][x]));
        }, out::println);

    }

    @Override
    public void prettyPrint(Matrix matrix) {
        prettyPrint(matrix, out);
    }
}
