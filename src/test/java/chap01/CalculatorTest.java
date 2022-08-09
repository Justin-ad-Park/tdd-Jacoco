package chap01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CalculatorTest {

    @Test
    void generator() {
        Calculator cal = new Calculator();
    }

    @Test
    void plus() {
        assertEquals(3, Calculator.plus(1,2));
        assertEquals(5, Calculator.plus(3,2));
    }

    @Test
    void plusNotEquals() {
        assertNotEquals(4, Calculator.plus(1,2));
    }
}
