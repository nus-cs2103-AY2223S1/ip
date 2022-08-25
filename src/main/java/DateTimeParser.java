import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

//referenced from
//https://www.geeksforgeeks.org/java-time-format-datetimeformatterbuilder-class-in-java/
//https://stackoverflow.com/questions/40175196/java-parsing-string-to-localdatetime-without-providing-time

public class DateTimeParser {
    // only allow format of yyyy-mm-dd as of now
    private static final DateTimeFormatter FORMATTER =  new DateTimeFormatterBuilder()
            .appendPattern("yyyy-MM-dd")
            .optionalStart()
            .appendPattern(" HH:mm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    public LocalDateTime getDateTime(String dateStr) {
        LocalDateTime dateTime = LocalDateTime.parse(dateStr, FORMATTER);
        return dateTime;
    }

    public String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }
}
