package labs.lab02;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;

public class Calculator {

    public static double calculateSumX(int[] x) {
        double sumX = 0;

        for (int value : x) {
            sumX += value;
        }

        return sumX;
    }

    public static double calculateWeightedSumX(int[] x) {
        double sumiX = 0;

        for (int i = 1; i <= x.length; i++) {
            sumiX += i * x[i - 1];
        }

        return sumiX;
    }

    public static double calculateF(
            double B,
            int n,
            double sumX,
            double sumiX
    ) {
        double lhs = 0;

        for (int i = 1; i <= n; i++) {
            lhs += 1.0 / (B - i + 1);
        }

        double rhs = n * sumX /
                ((B + 1) * sumX - sumiX);

        return lhs - rhs;
    }

    public static double calculateB(
            int n,
            double sumX,
            double sumiX
    ) {
        BrentSolver solver =
                new BrentSolver(1e-14, 1e-12);

        UnivariateFunction f =
                B -> calculateF(B, n, sumX, sumiX);

        return solver.solve(1000, f, n, 200);
    }

    public static double calculateK(
            int n,
            double B,
            double sumX,
            double sumiX
    ) {
        return n / ((B + 1) * sumX - sumiX);
    }

    public static double calculateNextX(
            double K,
            double B,
            int n
    ) {
        return 1.0 / (K * (B - n));
    }

    public static double calculateHarmonicSum(int m) {
        double sum = 0;

        for (int i = 1; i <= m; i++) {
            sum += 1.0 / i;
        }

        return sum;
    }

    public static double calculateTK(
            double B,
            int n,
            double K
    ) {
        int m = (int) Math.round(B - n);

        return calculateHarmonicSum(m) / K;
    }
}

