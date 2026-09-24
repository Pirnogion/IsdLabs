package labs.lab05;

public class Main {

    public static void main(String[] args) {
        try {
            // 1. Загрузка данных
            InputData data = InputData.load("parameters_lab05");
            QualityCalculator calc = new QualityCalculator(data);

            // 2. Генерация выборок
            double[] tvSample = calc.uniformSample(data.TvMin, data.TvMax, data.Ntv);
            double[] tpSample = calc.uniformSample(data.TpMin, data.TpMax, data.Ntp);
            double tvAvg = calc.mean(tvSample);
            double tpAvg = calc.mean(tpSample);

            // 3. Расчёт оценочных элементов
            double n0401 = calc.n0401();
            double n0501 = calc.n0501(tvAvg);
            double n0502 = calc.n0502(tpAvg);

            // 4. Расчёт двух метрик
            // Метрика 4 — функционирование в заданных режимах
            double m4 = n0401;

            // Метрика 5 — обеспечение обработки заданного объёма информации
            double m5 = calc.metricAverage(n0501, n0502);

            // 5. Абсолютный показатель единственного критерия Н2
            //    Веса метрик внутри критерия одинаковы: 0.5 и 0.5
            double[] metricsH2 = {m4, m5};
            double[] weightsH2 = {0.5, 0.5};
            double pH2 = calc.criterionAbs(metricsH2, weightsH2);

            // 6. Относительный показатель критерия Н2
            double kH2 = calc.criterionRel(pH2, data.baseCriterion);

            // 7. Фактор надёжности: критерий один, вес = 1
            double[] kCriteria      = {kH2};
            double[] weightsFactors = {1.0};
            double kFactor = calc.factor(kCriteria, weightsFactors);

            // 8. Вывод результатов
            System.out.println("=========================================================");
            System.out.println("  Оценка фактора надёжности по ГОСТ 28195-89");
            System.out.println("=========================================================");
            System.out.println("Исходные данные:");
            System.out.printf("  Q = %d, N = %d%n", data.Q, data.N);
            System.out.printf("  Tв ~ U[%.2f; %.2f], выборка %d%n", data.TvMin, data.TvMax, data.Ntv);
            System.out.printf("  Tв_доп = %.2f с%n", data.TvDop);
            System.out.printf("  Tпi ~ U[%.2f; %.2f], выборка %d%n", data.TpMin, data.TpMax, data.Ntp);
            System.out.printf("  Tпi_доп = %.2f с%n", data.TpDop);
            System.out.printf("  Базовый критерий = %.2f%n%n", data.baseCriterion);

            System.out.println("Средние значения выборок:");
            System.out.printf("  Tв  = %.4f с%n", tvAvg);
            System.out.printf("  Tпi = %.4f с%n%n", tpAvg);

            System.out.println("Оценочные элементы:");
            System.out.printf("  Н0401 (вероятность безотказной работы) = %.4f%n", n0401);
            System.out.printf("  Н0501 (оценка по Tв)                   = %.4f%n", n0501);
            System.out.printf("  Н0502 (оценка по Tпi)                  = %.4f%n%n", n0502);

            System.out.println("Метрики:");
            System.out.printf("  Метрика 4 (функционирование в заданных режимах)    = %.4f%n", m4);
            System.out.printf("  Метрика 5 (обеспечение обработки заданного объёма) = %.4f%n%n", m5);

            System.out.println("Критерий Н2 «Работоспособность»:");
            System.out.printf("  Абсолютный показатель  P_Н2 = %.4f%n", pH2);
            System.out.printf("  Относительный показатель K_Н2 = %.4f%n%n", kH2);

            System.out.println("Итоговый фактор надёжности:");
            System.out.printf("  K_надёжность = %.4f  (критерий один, вес = 1)%n", kFactor);
            System.out.println("=========================================================");

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}