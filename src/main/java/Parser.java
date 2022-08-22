import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static void main(String[] args) {
        try {
            String date = parseDateTime("12-1-6 1600").toString();
            LocalDateTime dateTime = LocalDateTime.parse(date);
            System.out.println(displayDateTime(dateTime));
            System.out.println(dateTime);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    private static final DateTimeFormatter PARSE_FORMAT = DateTimeFormatter.ofPattern("[yyyy][yy]-M-d HHmm");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, KK:mm a");

    public static LocalDateTime parseDateTime(String str) throws DukeException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(str, PARSE_FORMAT);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println(e);
            throw new DukeException("Exception: Cannot parse datetime.");
        }
    }

    public static String displayDateTime(LocalDateTime dateTime) {
        return dateTime.format(PRINT_FORMAT);
    }
}
