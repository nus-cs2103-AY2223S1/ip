package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Helper class to parse date/time strings
 */
public class ParsedDateTime {
    private static final DateTimeFormatter[] formatters = {
        DateTimeFormatter.ofPattern("yyyy-MM-dd"), DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),
        DateTimeFormatter.BASIC_ISO_DATE, DateTimeFormatter.ISO_LOCAL_DATE, DateTimeFormatter.ISO_OFFSET_DATE,
        DateTimeFormatter.ISO_DATE, DateTimeFormatter.ISO_LOCAL_TIME, DateTimeFormatter.ISO_OFFSET_TIME,
        DateTimeFormatter.ISO_TIME, DateTimeFormatter.ISO_LOCAL_DATE_TIME, DateTimeFormatter.ISO_OFFSET_DATE_TIME,
        DateTimeFormatter.ISO_ZONED_DATE_TIME, DateTimeFormatter.ISO_DATE_TIME, DateTimeFormatter.ISO_ORDINAL_DATE,
        DateTimeFormatter.ISO_WEEK_DATE, DateTimeFormatter.ISO_INSTANT, DateTimeFormatter.RFC_1123_DATE_TIME };

    private Optional<LocalDateTime> parsedDateTime;
    private String input;

    /**
     * Creates an object to handle if the date/time can be parsed.
     * @param input String that may represent date/time.
     */
    public ParsedDateTime(String input) {
        this.input = input;
        parsedDateTime = Optional.empty();
        for (DateTimeFormatter formatter : formatters) {
            try {
                parsedDateTime = Optional.of(LocalDateTime.parse(input, formatter));
                break;
            } catch (DateTimeParseException ex) {
                // Just try another one
            }
        }
    }

    /**
     * Gets a nicely-formatted date.
     * @return The date if it parses, else the original string.
     */
    @Override
    public String toString() {
        return parsedDateTime.map((dateTime) -> {
            return dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HHmm"));
        }).orElse(input);
    }
}
