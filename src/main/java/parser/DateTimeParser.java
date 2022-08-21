package parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser {
   private static DateTimeFormatter parsingFormatter = java.time.format.DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a");
   private static DateTimeFormatter readingFormatter = java.time.format.DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a");

    public static LocalDateTime changeStringToParsingDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeParser.parsingFormatter);
    }

    public static LocalDateTime changeStringToReadingDateTime(String str) {
        return LocalDateTime.parse(str, DateTimeParser.readingFormatter);
    }

    public static String changeDateTimeFormat(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("d-MMM-yyyy hh:mm a"));
    }
}
