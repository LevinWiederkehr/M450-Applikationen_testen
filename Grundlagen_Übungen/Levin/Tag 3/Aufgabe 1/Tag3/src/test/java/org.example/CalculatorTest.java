package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Calculator class using JUnit 5.
 * Tests all arithmetic operations and edge cases.
 */
@DisplayName("Calculator Tests")
class CalculatorTest {

    private org.example.Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Nested
    @DisplayName("Addition Tests")
    class AdditionTests {

        @Test
        @DisplayName("Adding two positive numbers")
        void testAddPositiveNumbers() {
            // Given
            double summand1 = 5.0;
            double summand2 = 3.0;
            double expected = 8.0;

            // When
            double result = calculator.add(summand1, summand2);

            // Then
            assertEquals(expected, result, 0.001,
                    "5.0 + 3.0 should equal 8.0");
        }

        @Test
        @DisplayName("Adding positive and negative number")
        void testAddPositiveAndNegative() {
            assertEquals(2.0, calculator.add(5.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Adding two negative numbers")
        void testAddNegativeNumbers() {
            assertEquals(-8.0, calculator.add(-5.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Adding zero to a number")
        void testAddZero() {
            assertEquals(5.0, calculator.add(5.0, 0.0), 0.001);
            assertEquals(0.0, calculator.add(0.0, 0.0), 0.001);
        }

        @ParameterizedTest
        @DisplayName("Parameterized addition tests")
        @CsvSource({
                "1.0, 1.0, 2.0",
                "2.5, 1.5, 4.0",
                "10.0, -5.0, 5.0",
                "0.0, 100.0, 100.0",
                "-10.0, -20.0, -30.0"
        })
        void testAddParameterized(double a, double b, double expected) {
            assertEquals(expected, calculator.add(a, b), 0.001);
        }
    }

    @Nested
    @DisplayName("Subtraction Tests")
    class SubtractionTests {

        @Test
        @DisplayName("Subtracting two positive numbers")
        void testSubtractPositiveNumbers() {
            assertEquals(2.0, calculator.subtract(5.0, 3.0), 0.001);
        }

        @Test
        @DisplayName("Subtracting negative number (double negative)")
        void testSubtractNegativeNumber() {
            assertEquals(8.0, calculator.subtract(5.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Subtracting larger from smaller number")
        void testSubtractLargerFromSmaller() {
            assertEquals(-2.0, calculator.subtract(3.0, 5.0), 0.001);
        }

        @Test
        @DisplayName("Subtracting zero")
        void testSubtractZero() {
            assertEquals(5.0, calculator.subtract(5.0, 0.0), 0.001);
        }

        @ParameterizedTest
        @CsvSource({
                "10.0, 5.0, 5.0",
                "1.0, 1.0, 0.0",
                "5.0, 10.0, -5.0",
                "0.0, 5.0, -5.0"
        })
        void testSubtractParameterized(double minuend, double subtrahend, double expected) {
            assertEquals(expected, calculator.subtract(minuend, subtrahend), 0.001);
        }
    }

    @Nested
    @DisplayName("Multiplication Tests")
    class MultiplicationTests {

        @Test
        @DisplayName("Multiplying two positive numbers")
        void testMultiplyPositiveNumbers() {
            assertEquals(15.0, calculator.multiply(5.0, 3.0), 0.001);
        }

        @Test
        @DisplayName("Multiplying positive and negative number")
        void testMultiplyPositiveAndNegative() {
            assertEquals(-15.0, calculator.multiply(5.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Multiplying two negative numbers")
        void testMultiplyNegativeNumbers() {
            assertEquals(15.0, calculator.multiply(-5.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Multiplying by zero")
        void testMultiplyByZero() {
            assertEquals(0.0, calculator.multiply(5.0, 0.0), 0.001);
            assertEquals(0.0, calculator.multiply(0.0, 5.0), 0.001);
        }

        @Test
        @DisplayName("Multiplying by one")
        void testMultiplyByOne() {
            assertEquals(5.0, calculator.multiply(5.0, 1.0), 0.001);
            assertEquals(-5.0, calculator.multiply(-5.0, 1.0), 0.001);
        }

        @ParameterizedTest
        @CsvSource({
                "2.0, 3.0, 6.0",
                "4.0, 0.5, 2.0",
                "-2.0, 3.0, -6.0",
                "-2.0, -3.0, 6.0",
                "0.0, 100.0, 0.0"
        })
        void testMultiplyParameterized(double factor1, double factor2, double expected) {
            assertEquals(expected, calculator.multiply(factor1, factor2), 0.001);
        }
    }

    @Nested
    @DisplayName("Division Tests")
    class DivisionTests {

        @Test
        @DisplayName("Dividing two positive numbers")
        void testDividePositiveNumbers() {
            assertEquals(2.0, calculator.divide(6.0, 3.0), 0.001);
        }

        @Test
        @DisplayName("Dividing positive by negative number")
        void testDividePositiveByNegative() {
            assertEquals(-2.0, calculator.divide(6.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Dividing two negative numbers")
        void testDivideNegativeNumbers() {
            assertEquals(2.0, calculator.divide(-6.0, -3.0), 0.001);
        }

        @Test
        @DisplayName("Dividing zero by number")
        void testDivideZeroByNumber() {
            assertEquals(0.0, calculator.divide(0.0, 5.0), 0.001);
        }

        @Test
        @DisplayName("Division by zero throws ArithmeticException")
        void testDivisionByZero() {
            ArithmeticException exception = assertThrows(
                    ArithmeticException.class,
                    () -> calculator.divide(5.0, 0.0),
                    "Division by zero should throw ArithmeticException"
            );
            assertEquals("Division by zero is not allowed", exception.getMessage());
        }

        @Test
        @DisplayName("Division by very small number")
        void testDivisionByVerySmallNumber() {
            double result = calculator.divide(1.0, 0.0001);
            assertEquals(10000.0, result, 0.001);
        }

        @ParameterizedTest
        @CsvSource({
                "10.0, 2.0, 5.0",
                "15.0, 3.0, 5.0",
                "1.0, 2.0, 0.5",
                "-10.0, 2.0, -5.0",
                "-10.0, -2.0, 5.0"
        })
        void testDivideParameterized(double dividend, double divisor, double expected) {
            assertEquals(expected, calculator.divide(dividend, divisor), 0.001);
        }
    }

    @Nested
    @DisplayName("Additional Utility Tests")
    class UtilityTests {

        @Test
        @DisplayName("Test positive number detection")
        void testIsPositive() {
            assertTrue(calculator.isPositive(5.0));
            assertTrue(calculator.isPositive(0.1));
            assertFalse(calculator.isPositive(0.0));
            assertFalse(calculator.isPositive(-5.0));
        }

        @Test
        @DisplayName("Test negative number detection")
        void testIsNegative() {
            assertTrue(calculator.isNegative(-5.0));
            assertTrue(calculator.isNegative(-0.1));
            assertFalse(calculator.isNegative(0.0));
            assertFalse(calculator.isNegative(5.0));
        }

        @ParameterizedTest
        @ValueSource(doubles = {1.0, 2.5, 100.0, 0.001})
        void testPositiveNumbers(double number) {
            assertTrue(calculator.isPositive(number));
        }

        @ParameterizedTest
        @ValueSource(doubles = {-1.0, -2.5, -100.0, -0.001})
        void testNegativeNumbers(double number) {
            assertTrue(calculator.isNegative(number));
        }
    }

    @Test
    @DisplayName("Test calculator instance not null after setup")
    void testCalculatorNotNull() {
        assertNotNull(calculator, "Calculator instance should not be null");
    }

    @Test
    @DisplayName("Complex calculation test")
    void testComplexCalculation() {
        // Test: (10 + 5) * 2 / 3 - 1
        double step1 = calculator.add(10.0, 5.0);        // 15
        double step2 = calculator.multiply(step1, 2.0);   // 30
        double step3 = calculator.divide(step2, 3.0);     // 10
        double result = calculator.subtract(step3, 1.0);  // 9

        assertEquals(9.0, result, 0.001);
    }
}