package ssuryaw5;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalcTest {

    @AfterEach
    void resetCalculatorValue(){
        Calc.reset();
    }


    // Test for * operation
    @Test
    void calculateTestForMultiplication(){
        assertEquals(30, Calc.calculate('*', 3));
    }

    @Test
    void calculateTestForDivision(){
        assertEquals(2,Calc.calculate('/', 5));
        assertThrows(ArithmeticException.class,() -> Calc.calculate('/', 0));
    }

    @Test
    void calculateTestForNegativeDivision(){
        assertEquals(-2,Calc.calculate('/', -5));
    }

    @Test
    void calculateTestForDivisionByGreaterDenominator(){
        assertEquals(0, Calc.calculate('/', 20));
    }

    @Test
    void calculateTestForAssign(){
        assertEquals(20, Calc.calculate('=', 20));
    }

    @Test
    void calculateTestForInvalidArgument(){
        assertThrows(IllegalArgumentException.class, () -> {Calc.calculate('$', 4);});
    }

    @Test
    void calculateTestForOverFlow(){
        assertThrows(ArithmeticException.class, () -> Calc.calculate('*', Integer.MAX_VALUE));
    }

}