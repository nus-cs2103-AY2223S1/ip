import java.rmi.UnexpectedException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeHandler {
    public static final DateTimeFormatter dukeDateTimeFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    public static final DateTimeFormatter storageDateTimeFormat = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    public static LocalDateTime formatDukeDateTime(String strDateTime) throws UnexpectedDateTimeFormatException {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(strDateTime, dukeDateTimeFormat);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new UnexpectedDateTimeFormatException();
        }
    }

    public static LocalDateTime formatStorageDateTime(String strTime) {
        LocalDateTime dateTime = LocalDateTime.parse(strTime, storageDateTimeFormat);
        return dateTime;
    }

}
