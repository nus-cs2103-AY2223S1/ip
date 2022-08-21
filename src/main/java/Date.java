import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;

/**
 * This class deals with dates in Dwuke.
 */
public class Date {
    /**
     * Converts the given String, in `dd-MM-yyyy` format, into a LocalDate object.
     *
     * @param s The String to convert.
     * @return The LocalDate object obtained from the String.
     * @throws DateTimeException If the given String is not of the correct format.
     */
    public static LocalDate parse(String s) throws DateTimeException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(s, formatter);
    }

    /**
     * Converts the given LocalDate object into a String, displayed in `d MMM yyy` format.
     *
     * @return A String representing the LocalDate.
     */
    public static String format(LocalDate localDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        return localDate.format(formatter);
    }
}
