package util.function.interfaces;

/**
 * @author Arthur Kupriyanov on 23.04.2020
 */
@FunctionalInterface
public interface TripleFunction {
    double apply(double x1, double x2, double x3);
}
