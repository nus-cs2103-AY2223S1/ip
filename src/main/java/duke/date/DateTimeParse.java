package duke.date;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import duke.exception.DukeException;

/**
 * Custom date time parser to handle more date time formats and natural dates.
 */
public class DateTimeParse {
    private static final LocalDate currentDate = LocalDate.now();
    private static final List<Deliminator> DELIMINATORS = Deliminator.getAllDeliminators();
    private static final Map<String, String> TIME_FORMAT_SUPPORTED = new LinkedHashMap<>() {
        final String amPmRegex = "(AM|PM)";
        final String padded12TimeRegex = "(?i)(1[0-2]|0[1-9]):[0-5][0-9]";
        final String nonPadded12TimeRegex = "(?i)(1[0-2]|0?[1-9]):[0-5][0-9]";
        final String padded12TimeRegexWithSpace = padded12TimeRegex + " " + amPmRegex;
        final String nonPadded12TimeRegexWithSpace = nonPadded12TimeRegex + " " + amPmRegex;
        final String padded12TimeRegexNoSpace = padded12TimeRegex + amPmRegex;
        final String nonPadded12TimeRegexNoSpace = nonPadded12TimeRegex + amPmRegex;
        final String paddedTimeRegex = "(([0-1][0-9]|2[0-3]):[0-5][0-9])";
        final String nonPaddedTimeRegex = "(([0-1]?[0-9]|2[0-3]):[0-5][0-9])";
        {
            put(padded12TimeRegexNoSpace, "hh:mma");
            put(nonPadded12TimeRegexNoSpace, "h:mma");
            put(padded12TimeRegexWithSpace, "hh:mm a");
            put(nonPadded12TimeRegexWithSpace, "h:mm a");
            put(paddedTimeRegex, "HH:mm");
            put(nonPaddedTimeRegex, "H:mm");
        }
    };
    private static final Map<String, String> DATETIME_FORMAT_REGEXPS = initialiseDateTimeFormatMap();

    /**
     * Parses a given date time string into a LocalDateTimeObject. This custom parse datetime
     * method can parse a wider variety of date and date time formats, such as with different
     * deliminators, ordering, and int padding. The parser will try to detect year or day patterns
     * so that the closest match can be made. However, in instances of ambiguous dates like 10/10/10,
     * the parser will assume a default format of dd/MM/yyyy. If no time is provided, the time
     * will be automatically set to the start of the day. If no year is provided, the year will
     * be automatically set the current year. If no month is provided, the month will be
     * automatically set to the current month. If no day is provided, the day will be
     * automatically set to the first day of the month.
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
                DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(dateTimeParseFormat)
                        .parseDefaulting(ChronoField.YEAR_OF_ERA, currentDate.getYear())
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, currentDate.getMonthValue())
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                        .toFormatter();
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
        return supportedFormats(datetimeFormatRegex);
    }

    private static void getDateFormatRegex(Map<String, String> dateFormats) {
        DELIMINATORS.forEach(d -> {
            new NumericalDateFormat(d, dateFormats).addNumericalDateFormats();
            new TextDateFormat(d, dateFormats).addTextDateFormats();
        });
    }

    private static LinkedHashMap<String, String> supportedFormats(Map<String, String> dateFormats) {
        LinkedHashMap<String, String> appendedDateTime = new LinkedHashMap<>();
        dateFormats.forEach((dateFormatRegex, dateFormatSequence) -> {
            // append the supported time formats to each supported date format
            appendTimeFormats(dateFormatRegex, dateFormatSequence, appendedDateTime);
            // add the start and end anchors to the date format regex
            dateFormatRegex = encloseRegex(dateFormatRegex);
            appendedDateTime.put(dateFormatRegex, dateFormatSequence);
        });
        return appendedDateTime;
    }

    private static void appendTimeFormats(String dateFormatRegex, String dateFormatSequence,
                                          Map<String, String> appendedDateTime) {
        TIME_FORMAT_SUPPORTED.forEach((timeFormatRegex, timeFormatSequence) -> {
            String dateTimeRegex = formatDateTimeRegex(dateFormatRegex, timeFormatRegex);
            String dateTimeFormatParse = formatParsedDateTime(dateFormatSequence, timeFormatSequence);
            appendedDateTime.put(dateTimeRegex, dateTimeFormatParse);
        });
    }

    private static String formatDateTimeRegex(String dateFormatRegex, String timeRegex) {
        return encloseRegex(dateFormatRegex + "\\s" + timeRegex);
    }

    private static String formatParsedDateTime(String dateFormatSequence, String timeFormatSequence) {
        return dateFormatSequence + " " + timeFormatSequence;
    }

    private static String encloseRegex(String regexExp) {
        return "^" + regexExp + "$";
    }

    private static LocalDateTime parseDateTimeWithFormatter(String dateTimeString,
                                                            DateTimeFormatter dateTimeFormatter) {
        try {
            return LocalDateTime.parse(dateTimeString, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            return LocalDate.parse(dateTimeString, dateTimeFormatter).atTime(LocalTime.now());
        }
    }
}
