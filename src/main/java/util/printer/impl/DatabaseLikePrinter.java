package util.printer.impl;

import model.Matrix;
import util.printer.MatrixPrinter;

import java.io.PrintStream;
import java.util.Optional;

import static java.lang.String.format;

/**
 * @author Arthur Kupriyanov on 28.02.2020
 */
public final class DatabaseLikePrinter implements MatrixPrinter {


    private static final char BORDER_KNOT = '+';
    private static final char HORIZONTAL_BORDER = '-';
    private static final char VERTICAL_BORDER = '|';

    private static final String DEFAULT_AS_NULL = "(NULL)";

    private final String asNull;

    public DatabaseLikePrinter() {
        this.asNull = DEFAULT_AS_NULL;
    }

    public void print(String[][] table, PrintStream out) {
        if (table == null) {
            throw new IllegalArgumentException("No tabular data provided");
        }
        if (table.length == 0) {
            return;
        }
        final int[] widths = new int[getMaxColumns(table)];
        adjustColumnWidths(table, widths);
        printPreparedTable(table, widths, getHorizontalBorder(widths), out);
    }

    private void printPreparedTable(String[][] table, int widths[], String horizontalBorder, PrintStream out) {
        final int lineLength = horizontalBorder.length();
        out.println(horizontalBorder);
        for (final String[] row : table) {
            if (row != null) {
                out.println(getRow(row, widths, lineLength));
                out.println(horizontalBorder);
            }
        }
    }

    private String getRow(String[] row, int[] widths, int lineLength) {
        final StringBuilder builder = new StringBuilder(lineLength).append(VERTICAL_BORDER);
        final int maxWidths = widths.length;
        for (int i = 0; i < maxWidths; i++) {
            builder.append(padRight(getCellValue(safeGet(row, i, null)), widths[i])).append(VERTICAL_BORDER);
        }
        return builder.toString();
    }

    private String getHorizontalBorder(int[] widths) {
        final StringBuilder builder = new StringBuilder(256);
        builder.append(BORDER_KNOT);
        for (final int w : widths) {
            for (int i = 0; i < w; i++) {
                builder.append(HORIZONTAL_BORDER);
            }
            builder.append(BORDER_KNOT);
        }
        return builder.toString();
    }

    private int getMaxColumns(String[][] rows) {
        int max = 0;
        for (final String[] row : rows) {
            if (row != null && row.length > max) {
                max = row.length;
            }
        }
        return max;
    }

    private void adjustColumnWidths(String[][] rows, int[] widths) {
        for (final String[] row : rows) {
            if (row != null) {
                for (int c = 0; c < widths.length; c++) {
                    final String cv = getCellValue(safeGet(row, c, asNull));
                    final int l = cv.length();
                    if (widths[c] < l) {
                        widths[c] = l;
                    }
                }
            }
        }
    }

    private static String padRight(String s, int n) {
        return format("%1$-" + n + "s", s);
    }

    private static String safeGet(String[] array, int index, String defaultValue) {
        return index < array.length ? array[index] : defaultValue;
    }

    private String getCellValue(Object value) {
        return value == null ? asNull : value.toString();
    }

    @Override
    public void prettyPrint(Matrix<?> matrix, PrintStream out) {
        final String[][] printMap = new String[matrix.getYSize()][matrix.getXSize()];

        matrix.consume((y, x) -> {
            Optional<? extends Number> element = matrix.getElement(x, y);
            if (element.isPresent()){
                printMap[y][x] = element.get().toString();
            } else {
                printMap[y][x] = "None";
            }
        });

        print(printMap, out);
    }
}