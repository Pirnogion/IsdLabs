package labs.lab02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    void calculateSumX_shouldReturnCorrectSum() {
        int[] x = {2, 4, 6};

        double result = Calculator.calculateSumX(x);

        assertEquals(12.0, result, 1e-12);
    }

    @Test
    void calculateWeightedSumX_shouldReturnCorrectSum() {
        int[] x = {2, 4, 6};

        double result = Calculator.calculateWeightedSumX(x);

        // 1*2 + 2*4 + 3*6 = 28
        assertEquals(28.0, result, 1e-12);
    }

    @Test
    void calculateHarmonicSum_shouldReturnCorrectSum() {
        double result = Calculator.calculateHarmonicSum(3);

        // 1 + 1/2 + 1/3
        assertEquals(1.8333333333333333, result, 1e-12);
    }

    @Test
    void calculateHarmonicSum_shouldReturnZeroForZero() {
        double result = Calculator.calculateHarmonicSum(0);

        assertEquals(0.0, result, 1e-12);
    }

    @Test
    void calculateF_shouldReturnExpectedValue() {
        int n = 3;
        double sumX = 12.0;
        double sumiX = 28.0;
        double B = 5.0;

        double result = Calculator.calculateF(
                B,
                n,
                sumX,
                sumiX
        );

        // lhs = 1/5 + 1/4 + 1/3
        // rhs = 3*12 / ((5+1)*12 - 28)
        double expected =
                (1.0 / 5 + 1.0 / 4 + 1.0 / 3)
                        - (3.0 * 12 / ((5 + 1) * 12 - 28));

        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculateK_shouldReturnExpectedValue() {
        int n = 3;
        double B = 5.0;
        double sumX = 12.0;
        double sumiX = 28.0;

        double result = Calculator.calculateK(
                n,
                B,
                sumX,
                sumiX
        );

        double expected =
                3.0 / ((5 + 1) * 12 - 28);

        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculateNextX_shouldReturnExpectedValue() {
        double K = 0.5;
        double B = 5.0;
        int n = 3;

        double result = Calculator.calculateNextX(K, B, n);

        double expected =
                1.0 / (0.5 * (5 - 3));

        assertEquals(expected, result, 1e-12);
    }

    @Test
    void calculateTK_shouldReturnExpectedValue() {
        double B = 6.0;
        int n = 3;
        double K = 2.0;

        double result = Calculator.calculateTK(B, n, K);

        // m = round(6 - 3) = 3
        // (1 + 1/2 + 1/3) / 2
        double expected =
                (1.0 + 1.0 / 2 + 1.0 / 3) / 2;

        assertEquals(expected, result, 1e-12);
    }
}