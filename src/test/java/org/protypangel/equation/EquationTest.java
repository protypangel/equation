package org.protypangel.equation;

import org.junit.jupiter.api.Test;
import org.protypangel.equation.math.Function;

import static org.junit.jupiter.api.Assertions.*;

class EquationTest {
    @Test void functions () {
        assertEquals(-1, Equation.cos.function(Math.PI));
        assertEquals( 0, Equation.around.function(Equation.sin.function(Math.PI), 5));
        assertEquals(0,  Equation.around.function(Equation.tan.function(Math.PI), 5));
        assertEquals(Math.E, Equation.exp.function(1));
        assertEquals(1, Equation.ln.function(Math.E));
        assertEquals(8, Equation.pow.function(2,3));
        assertEquals(3, Equation.racine.function(9,2));
        assertEquals(2, Equation.log.function(100,10));
    }
    @Test void around() {
        assertEquals(5.25, Equation.around.function(5.2540, 2));
        assertEquals(5.26, Equation.around.function(5.2560, 2));
        assertEquals(5.25, Equation.around.function(5.2550, 2));
        assertEquals(5.26, Equation.around.function(5.2556, 2));
        assertEquals(5.25, Equation.around.function(5.25553, 2));
        assertEquals(5.26, Equation.around.function(5.25556, 2));
        assertEquals(5.25, Equation.around.function(5.2555, 2));
    }
    @Test void functionAX2_BX_C () {
        Function function = Equation.getFunction("0x^4+x^3+2x^2+5x-3");
        assertEquals(-3, function.function(0));
        assertEquals(5, function.function(1));
        assertEquals(23, function.function(2));
    }
    @Test void functionEXPX_AX_B() {
        Function function = Equation.getFunction("ln(x^2+1)+x");
        assertEquals(0, function.function(0));
        assertEquals(1.69, Equation.around.function(function.function(1), 2));
        assertEquals(3.61, Equation.around.function(function.function(2), 2));
    }
    @Test void sum () {
        Function function = Equation.getFunction("x");
        assertEquals(6, Equation.sum(0, 3,1,function, true));
    }
}