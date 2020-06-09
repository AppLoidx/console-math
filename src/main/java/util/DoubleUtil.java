package util;

/**
 * @author Arthur Kupriyanov on 08.04.2020
 */
public final class DoubleUtil {
    private DoubleUtil() {
    }

    /**
     * Check is equal with accuracy
     *
     * @param d1  first value
     * @param d2  second value
     * @param acc accuracy (like 0.001d, 0.00001d and etc)
     * @return <code>true</code> if equals, else <code>false</code>
     */
    public static boolean isEqual(double d1, double d2, double acc) {
        double delta = Math.abs(d1 - d2);
        if (acc < 0) acc = -acc;    // accuracy should be positive
        return delta < acc && delta > -acc;
    }

    /**
     * Round double value with accuracy
     *
     * @param value round value
     * @param acc   accuracy
     * @return rounded value
     */
    public static double round(double value, double acc) {
        return Math.round(value / acc) / (1 / acc);
    }

}
