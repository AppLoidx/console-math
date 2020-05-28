package core;

import util.function.ExtendedFunction;
import util.function.interfaces.Dot;


import java.util.List;

/**
 * @author Arthur Kupriyanov on 21.05.2020
 */
public interface Interpolator {
    /**
     *
     * @param dots (x, y) coordinates
     * @return interpolation function
     */
    ExtendedFunction interpolate(List<Dot> dots);
}
