import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class DateTime {
    private static final DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("yyyy-MM-dd")
            .optionalStart().appendPattern(" HHmm").optionalEnd().parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0).toFormatter();
    private String input;
    private LocalDateTime dateTime;

    public DateTime(String input) {
        this.input = input;
        try {
            dateTime = LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }
}
