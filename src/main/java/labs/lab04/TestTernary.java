package labs.lab04;

public class TestTernary {

    public static void main(String[] args) {
        boolean flag = true;

        // Пример 1: неожиданное приведение типов
        Number n = flag ? new Integer(1) : new Double(2.0);
        System.out.println("n = " + n);

        // Пример 2: возможный NullPointerException
        boolean flag1 = false;
        boolean flag2 = false;
        Integer n2 = flag1 ? 1 : flag2 ? 2 : null;
        System.out.println("n2 = " + n2);

        // Пример 3: возврат null из метода с примитивным типом
        TestTernary test = new TestTernary();
        System.out.println("getVal(0) = " + test.getVal(0));
        System.out.println("getVal(5) = " + test.getVal(5));
    }

    private static final double[] vals = new double[] {1.0, 2.0, 3.0};

    // Проблема: метод возвращает примитивный тип double,
    // но тернарный оператор пытается вернуть null
    double getVal(int idx) {
        return (idx < 0 || idx >= vals.length) ? null : vals[idx];
    }
}