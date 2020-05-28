package core.impl;

import org.junit.jupiter.api.Test;
import util.function.ExtendedFunction;
import util.function.SimpleDot;
import util.function.interfaces.Dot;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Arthur Kupriyanov on 28.05.2020
 */
class NewtonInterpolatorTest {
   @Test
   public void testInterpolation() {
       List<Dot> points = new ArrayList<>();
       points.add(new SimpleDot(0.33, 3.79));
       points.add(new SimpleDot(0.2, 2.38));
       points.add(new SimpleDot(0.15, 1.25));
       points.add(new SimpleDot(0.47, 5.54));
       points.add(new SimpleDot(0.62, 7.14));

       ExtendedFunction func = new NewtonInterpolator().interpolate(points);
       double x1, x2;
       System.out.println(x1 = func.apply(0.22));

       List<Dot> points2 = new ArrayList<>();
       points2.add(new SimpleDot(0.47, 5.44));
       points2.add(new SimpleDot(0.33, 3.79));
       points2.add(new SimpleDot(0.2, 2.38));
       ExtendedFunction func2 = new NewtonInterpolator().interpolate(points2);
       System.out.println(x2 = func2.apply(0.22));

       System.out.println((x1 + x2) / 2);
   }
}