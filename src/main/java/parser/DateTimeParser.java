package parser;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

public class DateTimeParser {

    DateTimeFormatter formatter;
    public DateTimeParser() {
        DateTimeFormatterBuilder builder = new DateTimeFormatterBuilder().parseCaseInsensitive();
        String[] dateFormats = {
                "dd-MM-yyyy",
                "yyyy-MM-dd",
                "d MMM yyyy",
                "MMM d yyyy",
        };
        for (String dateFormat : dateFormats) {
            builder.appendPattern(dateFormat);
        }
        String[] dateTimeFormats = {
                "dd-MM-yyyy H:mm",
                "dd-MM-yyyy H:mm a",
                "yyyy-MM-dd H:mm",
                "yyyy-MM-dd H:mm a",
                "d MMM yyyy H:mm",
                "d MMM yyyy H:mm a",
                "MMM d yyyy H:mm",
                "MMM d yyyy H:mm a"
        };
        for (String dateTimeFormat : dateTimeFormats) {
            builder.appendPattern(dateTimeFormat);
        }
        this.formatter = builder.toFormatter(Locale.ENGLISH);
    }

    /**
     * Returns a string in the appropriate date ("d MMM yyyy") or datetime ("d MMM yyyy H:mm a") formats.
     *
     * @param inputDateTime The input date time to parse
     * @return A string in the appropriate date or datetime formats
     */
    public String parse(String inputDateTime) {

    }
}
