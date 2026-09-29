package labs.lab02;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] x = TestingData
                .load("parameters_lab02")
                .intervals();

        int n = x.length;

        double sumX = Calculator.calculateSumX(x);
        double sumiX = Calculator.calculateWeightedSumX(x);

        double B = Calculator.calculateB(n, sumX, sumiX);
        System.out.println("B = " + B);

        double K = Calculator.calculateK(n, B, sumX, sumiX);
        System.out.println("K = " + K);

        double xNext = Calculator.calculateNextX(K, B, n);
        System.out.println("X_" + (n + 1) + " = " + xNext);

        double tK = Calculator.calculateTK(B, n, K);
        System.out.println("t_k = " + tK);
    }
}