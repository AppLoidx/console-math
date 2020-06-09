package util.printer;

import model.Matrix;

import java.io.PrintStream;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public interface MatrixPrinter {
    void prettyPrint(Matrix matrix, PrintStream out);

    void prettyPrint(Matrix matrix);
}
