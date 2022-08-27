package blob.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

public class DateTimeParser {

    private final String[] dateFormats = {
            "dd-MM-yyyy",
            "yyyy-MM-dd",
            "d MMM yyyy",
            "MMM d yyyy",
    };
    private final String[] dateTimeFormats = {
            "dd-MM-yyyy HH:mm",
            "yyyy-MM-dd HH:mm",
            "d MMM yyyy HH:mm",
            "MMM d yyyy HH:mm",
    };
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter dateTimeFormatter;

    public DateTimeParser() {
        DateTimeFormatterBuilder dateFormatterBuilder = new DateTimeFormatterBuilder().parseCaseInsensitive();
        for (String dateFormat : dateFormats) {
            dateFormatterBuilder.appendPattern("[" + dateFormat + "]");
        }

        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder().parseCaseInsensitive();
        for (String dateTimeFormat : dateTimeFormats) {
            dateTimeFormatterBuilder.appendPattern("[" + dateTimeFormat + "]");
        }
        this.dateFormatter = dateFormatterBuilder.toFormatter(Locale.ENGLISH);
        this.dateTimeFormatter = dateTimeFormatterBuilder.toFormatter(Locale.ENGLISH);
    }

    /**
     * Returns a string in the appropriate date ("d MMM yyyy") or datetime ("d MMM yyyy H:mm a") formats.
     *
     * @param inputDateTime The input datetime to parse.
     * @return A string in the appropriate date or datetime formats
     */
    public String parse(String inputDateTime) throws DateTimeParseException {
        try {
            LocalDate parsedDate = LocalDate.parse(inputDateTime, dateFormatter);
            return parsedDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } catch (DateTimeParseException exception) {
            // Try to parse as datetime instead
            LocalDateTime parsedDateTime = LocalDateTime.parse(inputDateTime, this.dateTimeFormatter);
            return parsedDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy HH:mm"));
        }
    }
}
