package blob.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * The DateTimeParser deals with parsing dates and datetimes of different formats
 */
public class DateTimeParser {

    /** The list of accepted date formats that the application will parse as input */
    private final String[] dateFormats = {
        "dd-MM-yyyy",
        "yyyy-MM-dd",
        "d MMM yyyy",
        "MMM d yyyy",
    };

    /** The list of accepted datetime formats that the application will parse as input */
    private final String[] dateTimeFormats = {
        "dd-MM-yyyy HH:mm",
        "yyyy-MM-dd HH:mm",
        "d MMM yyyy HH:mm",
        "MMM d yyyy HH:mm",
    };

    /** The formatter that will be used to convert parsed date formats into the output format */
    private final DateTimeFormatter dateFormatter;

    /** The formatter that will be used to convert parsed datetime formats into the output format */
    private final DateTimeFormatter dateTimeFormatter;

    /**
     * Constructs a DateTimeParser that parses strings of different date and datetime formats
     * into a string of a proper date or datetime format
     */
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
