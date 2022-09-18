import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.format.DateTimeFormatter; // Import the DateTimeFormatter class
import java.time.format.DateTimeParseException;

public class DateTime {
    private static DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM d yyyy HHmm");

    public static LocalDateTime parseDate(String date) throws DukeException{
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(date, inputFormat);
            return parsedDate;
        } catch (DateTimeParseException e) {
            throw new DukeException("Hmm... your date and time has to bee in YYYY-MM-DD HHmm format!");
        }
    }

    public static String printDate(LocalDateTime date) {
        return date.format(outputFormat);
    }
}
