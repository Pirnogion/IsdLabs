package labs.lab02;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.solvers.BrentSolver;

import java.io.IOException;

public class Main {
    static int[] X;
    static int n;
    static double sumX;
    static double sumiX;

    public static void main(String[] args) throws IOException {
        X = TestingData.load("parameters_lab02").intervals();
        n = X.length;

        sumX = 0;
        sumiX = 0;
        for (int i = 1; i <= n; i++) {
            sumX += X[i - 1];
            sumiX += i * X[i - 1];
        }

        double relativeAccuracy = 1e-14;
        double absoluteAccuracy = 1e-12;
        BrentSolver solver = new BrentSolver(relativeAccuracy, absoluteAccuracy);

        UnivariateFunction f = Main::f;
        double B = solver.solve(1000, f, n, 200);
        System.out.println("B = " + B);

        double K = n / ((B + 1) * sumX - sumiX);
        System.out.println("K = " + K);

        double xNext = 1 / (K * (B - n));
        System.out.println("X_" + (n + 1) + " = " + xNext);

        int m = (int) Math.round(B - n);
        double summa = 0;
        for (int i = 1; i <= m; i++) {
            summa += 1.0 / i;
        }
        double tK = summa / K;
        System.out.println("t_k = " + tK);
    }

    static double f(double B) {
        double lhs = 0;
        for (int i = 1; i <= n; i++) {
            lhs += 1.0 / (B - i + 1);
        }
        double rhs = n * sumX / ((B + 1) * sumX - sumiX);
        return lhs - rhs;
    }
}