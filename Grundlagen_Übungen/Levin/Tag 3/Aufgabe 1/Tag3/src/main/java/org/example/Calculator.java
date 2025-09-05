package org.example;

/**
 * Simple Calculator class with basic arithmetic operations.
 * Supports addition, subtraction, multiplication and division.
 */
public class Calculator {

    /**
     * Adds two double values.
     *
     * @param summand1 first summand
     * @param summand2 second summand
     * @return sum of summand1 and summand2
     */
    public double add(double summand1, double summand2) {
        return summand1 + summand2;
    }

    /**
     * Subtracts the second value from the first value.
     *
     * @param minuend the value to subtract from
     * @param subtrahend the value to subtract
     * @return difference of minuend and subtrahend
     */
    public double subtract(double minuend, double subtrahend) {
        return minuend - subtrahend;
    }

    /**
     * Multiplies two double values.
     *
     * @param factor1 first factor
     * @param factor2 second factor
     * @return product of factor1 and factor2
     */
    public double multiply(double factor1, double factor2) {
        return factor1 * factor2;
    }

    /**
     * Divides the first value by the second value.
     *
     * @param dividend the value to be divided
     * @param divisor the value to divide by
     * @return quotient of dividend and divisor
     * @throws ArithmeticException if divisor is zero
     */
    public double divide(double dividend, double divisor) {
        if (divisor == 0.0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        return dividend / divisor;
    }

    /**
     * Checks if a number is positive.
     *
     * @param number the number to check
     * @return true if number is greater than zero, false otherwise
     */
    public boolean isPositive(double number) {
        return number > 0;
    }

    /**
     * Checks if a number is negative.
     *
     * @param number the number to check
     * @return true if number is less than zero, false otherwise
     */
    public boolean isNegative(double number) {
        return number < 0;
    }
}