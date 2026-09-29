package labs.lab04;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDate {
    // Проблема: статический DateFormat не потокобезопасен
    private static final DateFormat format =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public String getDate() {
        return format.format(new Date());
    }

    public static void main(String[] args) {
        TestDate test = new TestDate();
        System.out.println(test.getDate());
    }
}