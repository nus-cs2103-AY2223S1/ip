package wanya.parser;

import java.time.DateTimeException;
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

    public static LocalDateTime getDateTime(String dateStr) throws DateTimeException {
        return LocalDateTime.parse(dateStr, FORMATTER);
    }

    public static String getDateTimeString(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);
    }

    public static String getDateTimeStorage(LocalDateTime dateTime) {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(dateTime);
    }
}
