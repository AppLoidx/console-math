package core.impl;

import org.junit.jupiter.api.Test;
import util.function.DiffEquation;
import util.function.ExtendedFunction;
import util.function.interfaces.Dot;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov on 01.06.2020
 */
class ImprovedEulerDiffEquationSolverTest {

    /**
     * Equation solve example from Computational Math Lecture Slides (ITMO)
     * see (rus) : https://i.imgur.com/PAirV9o.png
     *
     * <br/><br/>
     * <img src="https://i.imgur.com/PAirV9o.png" />
     */
    @Test
    public void exampleFromLecture() {
        ImprovedEulerDiffEquationSolver solver = new ImprovedEulerDiffEquationSolver();
        DiffEquation function = new DiffEquation((x, y) -> y + (1 + x) * y * y);

        List<Dot> dots = solver.solve(function, 1, -1);

        System.out.println(dots);
    }

}