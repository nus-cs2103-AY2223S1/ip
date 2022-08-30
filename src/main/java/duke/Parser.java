package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Converts date from string representing the date to LocalDate object.
     *
     * @param string date in string.
     * @return LocalDate LocalDate object.
     */
    public static LocalDate stringToDate(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate myDateObj = LocalDate.parse(string, formatter);
        return myDateObj;
    }

    /**
     * Converts date from LocalDate object to string representing the date.
     *
     * @param date date represented as LocalDate object.
     * @return LocalDate date object converted from string.
     */
    public static String dateToString(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    /**
     * Converts LocalDate object to string of a pattern representing the date
     *
     * @param date date represented as LocalDate object.
     * @return String converted from LocalDate.
     */
    public static String displayDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}

