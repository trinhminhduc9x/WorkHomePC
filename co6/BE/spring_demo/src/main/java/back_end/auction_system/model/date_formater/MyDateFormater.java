package case_study.furama_resort.model.date_formater;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyDateFormater {
    private static final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean checkDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, fmt);
        } catch (Exception e) {
            return false;
        }
        return true;
    }


}
