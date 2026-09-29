package labs.lab04;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args) {
        // Проблема: BigDecimal от double дает неточное значение
        System.out.println(new BigDecimal(1.1));
        // Результат: 1.100000000000000088817841970012523233890533447265625

        // Правильный способ:
        System.out.println(new BigDecimal("1.1"));

        // Проблема с equals:
        BigDecimal d1 = new BigDecimal("1.1");
        BigDecimal d2 = new BigDecimal("1.10");
        System.out.println(d1.equals(d2)); // false
    }
}