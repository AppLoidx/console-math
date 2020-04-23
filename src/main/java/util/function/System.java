package util.function;

import util.function.interfaces.DoubleFunction;
import util.function.interfaces.FourFunction;
import util.function.interfaces.TripleFunction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Arthur Kupriyanov on 23.04.2020
 */
public class System {
    private final List<SystemFunction> extendedFunctions;

    public System(SystemFunction ... functions){
        extendedFunctions = List.of(functions);
    }

    public System(DoubleFunction ... functions) {
        extendedFunctions = new ArrayList<>();
        for (DoubleFunction df : functions) {
            extendedFunctions.add(new SystemFunction(df));
        }
    }

    public System(TripleFunction... functions) {
        extendedFunctions = new ArrayList<>();
        for (TripleFunction df : functions) {
            extendedFunctions.add(new SystemFunction(df));
        }
    }

    public System(FourFunction... functions) {
        extendedFunctions = new ArrayList<>();
        for (FourFunction df : functions) {
            extendedFunctions.add(new SystemFunction(df));
        }
    }



}
