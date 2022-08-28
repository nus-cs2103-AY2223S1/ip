import java.time.temporal.ChronoUnit;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTime {
    private static final DateTimeFormatter INPUT_FORMAT_1 = DateTimeFormatter.ofPattern("yyyy-dd-MM HH:mm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyy HH:mm");
    
    public static String parseToString(String timeInput) {
        try {
            return OUTPUT_FORMAT.format(parseToLocalDateTime(timeInput));
        } catch (IanaException e) {
            return timeInput;
        }
    }

    public static LocalDateTime parseToLocalDateTime(String timeInput) throws IanaException {
        LocalDateTime timeOutput;

        try {
             timeOutput = LocalDateTime.parse(timeInput, INPUT_FORMAT_1);
        } catch (DateTimeParseException e) {
            throw new IanaException("Wrong date time format!!");
        }

        return timeOutput;
    }
}
