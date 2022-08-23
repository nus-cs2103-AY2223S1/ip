import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static LocalDate convertToDateObj(String string) throws DukeException {
        try {
            return LocalDate.parse(string);
        } catch (DateTimeException e) {
            throw new DukeException("Please Input a Valid Date");
        }
    }

    public static String printDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    public static String printSaveFileDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
