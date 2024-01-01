package lk.ijse.sellingLk.util;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;

public class DateTimeUtil {
    public static String dateNow() {
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy");
        return format.format(new Date());
    }

    public static String yearNow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(new Date());
    }

    public static int getDays(int year, int month) {
        YearMonth yearMonthObject = YearMonth.of(year, month);
        return yearMonthObject.lengthOfMonth();

    }

    public static String monthNow() {
        LocalDate localDate = LocalDate.now();
        return String.valueOf(localDate.getMonth());
    }

    public static String timeNow() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("hh:mm aa");
               return dateFormat.format(new Date()) ;
    }
    public static String timeNowForName() {
        SimpleDateFormat dateFormat=new SimpleDateFormat("HH_mm");
               return dateFormat.format(new Date()) ;
    }
}
