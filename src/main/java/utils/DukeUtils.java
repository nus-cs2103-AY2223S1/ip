package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DukeUtils {

    public static LocalDate parseDate(String inputDate) {
        return LocalDate.parse(inputDate);
    }

    public static String convertLocalDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    public static void printWithIndent(String message) {
        System.out.println("\t" + message);
    }

    public static void printMessages(String... messages) {
        printLine();
        for (String m : messages) {
            printWithIndent(m);
        }
        printLine();
    }

    public static void printLine() {
        System.out.println("\t────────────────────────────────────────────────────────────");
    }
}
