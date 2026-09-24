package labs.lab05;

import java.util.Random;

public class QualityCalculator {

    private final InputData data;
    private final Random random = new Random();

    public QualityCalculator(InputData data) {
        this.data = data;
    }

    /** Равномерная выборка [min, max] объёма n */
    public double[] uniformSample(double min, double max, int n) {
        double[] s = new double[n];
        for (int i = 0; i < n; i++) s[i] = min + (max - min) * random.nextDouble();
        return s;
    }

    /** Среднее значение выборки */
    public double mean(double[] s) {
        double sum = 0;
        for (double v : s) sum += v;
        return sum / s.length;
    }

    /** Н0401: вероятность безотказной работы P = 1 - Q/N */
    public double n0401() {
        return 1.0 - (double) data.Q / data.N;
    }

    /** Н0501: Qв = 1, если Tв <= Tв_доп; Qв = Tв_доп/Tв иначе */
    public double n0501(double tv) {
        return tv <= data.TvDop ? 1.0 : data.TvDop / tv;
    }

    /** Н0502: Qпi = 1, если Tпi <= Tпi_доп; Qпi = Tпi_доп/Tпi иначе */
    public double n0502(double tp) {
        return tp <= data.TpDop ? 1.0 : data.TpDop / tp;
    }

    /** Средняя оценка метрики (формула 3) */
    public double metricAverage(double... values) {
        double s = 0;
        for (double v : values) s += v;
        return s / values.length;
    }

    /** Абсолютный показатель критерия (формула 4) */
    public double criterionAbs(double[] metricValues, double[] weights) {
        double s = 0;
        for (int i = 0; i < metricValues.length; i++) s += metricValues[i] * weights[i];
        return s;
    }

    /** Относительный показатель критерия (формула 5) */
    public double criterionRel(double p, double base) {
        return p / base;
    }

    /** Фактор качества (формула 6) */
    public double factor(double[] kCriteria, double[] weights) {
        double s = 0;
        for (int i = 0; i < kCriteria.length; i++) s += kCriteria[i] * weights[i];
        return s;
    }
}