package duke.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import duke.exception.DukeException;

/**
 * Custom date time parser to handle more date time formats and natural dates.
 */
public class DateTimeParse {
    private static final List<Deliminator> DELIMINATORS = Deliminator.getAllDeliminators();
    private static final Map<String, String> DATETIME_FORMAT_REGEXPS = initialiseDateTimeFormatMap();
    private static final String TIME_REGEX = "(([0-1]?[0-9]|2[0-3]):[0-5][0-9])";

    /**
     * Parses a given date time string into a LocalDateTimeObject. This custom parse datetime
     * method can parse a wider variety of date and date time formats, such as with different
     * deliminators, ordering, and int padding. The parser will try to detect year or day patterns
     * so that the closest match can be made. However, in instances of ambiguous dates like 10/10/10,
     * the parser will assume a default format of dd/MM/yyyy. Also, if no time is provided, the
     * time will be automatically set to the start of the day.
     *
     * @param dateTimeString The datetime string to be parsed into a datetime object.
     * @return The datetime object parsed from the date time string.
     * @throws DukeException If the datetime string could not be parsed due to unsupported or
     *                       invalid datetime format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        for (String regexp : DATETIME_FORMAT_REGEXPS.keySet()) {
            if (dateTimeString.toLowerCase().matches(regexp)) {
                String dateTimeParseFormat = DATETIME_FORMAT_REGEXPS.get(regexp);
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeParseFormat);
                try {
                    return parseDateTimeWithFormatter(dateTimeString, dateTimeFormatter);
                } catch (DateTimeParseException e) {
                    throw new DukeException(String.format("Unknown date format %s!", dateTimeString));
                }
            }
        }
        throw new DukeException(String.format("Unknown date format %s!", dateTimeString));
    }

    private static Map<String, String> initialiseDateTimeFormatMap() {
        Map<String, String> datetimeFormatRegex = new LinkedHashMap<>();
        // regex ordered from most to least specific so that the best match can be taken
        // populate all date formats first
        getDateFormatRegex(datetimeFormatRegex);
        return appendTimeFormats(datetimeFormatRegex);
    }

    private static void getDateFormatRegex(Map<String, String> dateFormats) {
        DELIMINATORS.forEach(d -> {
            NumericalDateFormat numericalDateFormat = new NumericalDateFormat(d);
            numericalDateFormat.addNumericalDateFormats(dateFormats);
        });
    }

    private static LinkedHashMap<String, String> appendTimeFormats(Map<String, String> dateFormats) {
        LinkedHashMap<String, String> appendedDateTime = new LinkedHashMap<>();
        dateFormats.forEach((dateFormatRegex, dateFormatParsed) -> {
            String dateTimeRegex = formatDateTimeRegex(dateFormatRegex);
            String dateTimeFormatParse = formatParsedDateTime(dateFormatParsed);
            dateFormatRegex = encloseRegex(dateFormatRegex);
            appendedDateTime.put(dateTimeRegex, dateTimeFormatParse);
            appendedDateTime.put(dateFormatRegex, dateFormatParsed);
        });
        return appendedDateTime;
    }

    private static String formatDateTimeRegex(String dateFormatRegex) {
        return encloseRegex(dateFormatRegex + "\\s" + TIME_REGEX);
    }

    private static String formatParsedDateTime(String dateFormatParsed) {
        return dateFormatParsed + " HH:mm";
    }

    private static String encloseRegex(String regexExp) {
        return "^" + regexExp + "$";
    }

    private static LocalDateTime parseDateTimeWithFormatter(String dateTimeString,
                                                            DateTimeFormatter dateTimeFormatter) {
        try {
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(dateTimeString, dateTimeFormatter).atStartOfDay();
        }
    }
}
