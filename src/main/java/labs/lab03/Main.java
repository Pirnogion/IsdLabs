package labs.lab03;

import java.io.IOException;
import java.util.Arrays;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        try {
            task1(Params1Data.load("parameters_lab03_1"));
            task2(Params2Data.load("parameters_lab03_2"));
            task3(Params3Data.load("parameters_lab03_3"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void task1(Params1Data data) {
        System.out.println("TASK #1");

        double n2s = n2s(data.maxTargets(), data.samplingRate(), data.inputParams(), data.outputParams());
        System.out.println("n2* = " + n2s + " (вх+вых)");

        double Vs = Vs(n2s);
        System.out.println("V* = " + Vs + " (потенциальный объем программы)");

        double B1 = B1(Vs, data.languageLevel());
        System.out.println("B = " + B1 + " (потенциальное число ошибок)");
    }

    static double n2s(double a, double b, double c, double d) {
        return a * b * c + a * d;
    }

    static double Vs(double n2) {
        double temp = (n2 + 2);
        return temp * log2(temp);
    }

    static double B1(double Vs, double y) {
        return (Vs * Vs) / (3000 * y);
    }

    static void task2(Params2Data data) {
        System.out.println("\nTASK #2");

        double n2s = n2s(data.maxTargets(), data.samplingRate(), data.inputParams(), data.outputParams());
        System.out.println("n2* = " + n2s + " (вх+вых)");

        double K = K(n2s);
        System.out.println("K = " + K + " (число модулей)");

        double N = N(K);
        System.out.println("N = " + N + " (длина программы)");

        double V = V(K);
        System.out.println("V = " + V + " (объем ПО)");

        double P = P(N);
        System.out.println("P = " + P + " (кол-во команд ассемблера)");

        double B2 = B2(V);
        System.out.println("B2 = " + B2 + " (потенциальное число ошибок)");

        double Tk = Tk(N, data.writtenPrograms(), data.incorrectPrograms());
        System.out.println("Tk = " + Tk + " (календарное время программирования)");

        double tn = tn(Tk, B2, data.workingHours());
        System.out.println("tn = " + tn + " (наработка на отказ)");
    }

    static double K(double n2) {
        double k = n2 / 8;
        return (k > 8) ? n2 / 8 : n2 / 8 + n2 / 64;
    }

    static double N(double K) {
        return 220 * K + K * log2(K);
    }

    static double V(double K) {
        return K * 220 * log2(48);
    }

    static double P(double N) {
        return 3 * N / 8;
    }

    static double Tk(double N, double m, double v) {
        return 3 * N / (8 * m * v);
    }

    static double B2(double V) {
        return V / 3000;
    }

    static double tn(double Tk, double B2, double wh) {
        return (Tk * wh) / (2 * Math.log(B2));
    }

    static double log2(double value) {
        return Math.log(value) / Math.log(2);
    }

    static void task3(Params3Data data) {
        System.out.println("\nTASK #3");

        double sumV = sum(data.programsLengths());
        double sumB = sum(data.programsErrors());

        System.out.println("Исходные данные (вариант 5):");
        System.out.println("  R0 = " + data.initialRating());
        System.out.println("  λ  = " + data.languageLevel());
        System.out.println("  Sum(Vj) = " + sumV + " Кбайт");
        System.out.println("  Sum(Bk) = " + sumB + " ошибок");
        System.out.println("  Планируемый объём новой программы = " + data.targetProgramLength() + " Кбайт");
        System.out.println();

        BiFunction<Double, Double, Double> c1 = (lam, r) -> 1.0 / (lam + r);
        BiFunction<Double, Double, Double> c2 = (lam, r) -> 1.0 / (lam * r);
        BiFunction<Double, Double, Double> c3 = (lam, r) -> 1.0 / lam + 1.0 / r;

        evaluateVariant("c(λ,R) = 1 / (λ + R)", c1, data.initialRating(), data.languageLevel(), sumV, sumB, data.targetProgramLength());
        evaluateVariant("c(λ,R) = 1 / (λ * R)", c2, data.initialRating(), data.languageLevel(), sumV, sumB, data.targetProgramLength());
        evaluateVariant("c(λ,R) = 1/λ + 1/R", c3, data.initialRating(), data.languageLevel(), sumV, sumB, data.targetProgramLength());
    }

    private static void evaluateVariant(String title, BiFunction<Double, Double, Double> c, double R0, double lambda, double sumV, double sumB, double plannedVolume) {
        double cAtR0 = c.apply(lambda, R0);
        double Ri = R0 * (1 + 1e-3 * (sumV - sumB / cAtR0));

        double cAtRi = c.apply(lambda, Ri);
        double expectedErrors = cAtRi * plannedVolume;

        System.out.println(title);
        System.out.println("  c(λ, R0) = " + cAtR0);
        System.out.println("  Текущий рейтинг R_i = " + Ri);
        System.out.println("  c(λ, R_i) = " + cAtRi);
        System.out.println("  Ожидаемое число ошибок B_(n+1) = " + expectedErrors);
        System.out.println();
    }

    private static double sum(double[] arr) {
        return Arrays.stream(arr).sum();
    }
}