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
import java.util.Set;

import duke.exception.DukeException;

/**
 * Custom date time parser to handle more date time formats and natural dates.
 */
public class DateTimeParse {
    protected static final List<Deliminator> DELIMINATORS = Deliminator.getAllDeliminators();
    private static final LocalDate currentDate = LocalDate.now();
    private static final Map<String, String> TIME_FORMAT_SUPPORTED = TimeFormat.getTimeFormats();
    private static final Map<String, String> DATETIME_FORMATS = initialiseDateTimeFormatMap();
    private static final Set<String> DATETIME_FORMATS_REGEXP = DATETIME_FORMATS.keySet();

    /**
     * Initialises the datetime format linked hashmap by adding all supported datetime formats.
     * @return LinkedHashMap of all supported datetime formats, where the key is the regex of
     *         the datetime format, and the value is the datetime format.
     */
    private static LinkedHashMap<String, String> initialiseDateTimeFormatMap() {
        Map<String, String> datetimeFormatRegex = new LinkedHashMap<>();
        // regex ordered from most to least specific so that the best match can be taken
        // populate all date formats first
        getDateFormatRegex(datetimeFormatRegex);
        return supportedFormats(datetimeFormatRegex);
    }

    /**
     * Parses a given date time string into a LocalDateTimeObject. This custom parse datetime
     * method can parse a wider variety of date and date time formats- such as natural
     * dates (today, tomorrow, yesterday...) and standard dates.
     *
     * @param dateTimeString The datetime string to be parsed into a datetime object.
     * @return The datetime object parsed from the date time string.
     * @throws DukeException If the datetime string could not be parsed due to unsupported or
     *                       invalid datetime format.
     */
    public static LocalDateTime parseDateTime(String dateTimeString) throws DukeException {
        String naturalDateRegExp = NaturalDate.getNaturalDateRegExp(dateTimeString);
        if (naturalDateRegExp == null) {
            // if the date string is not a natural date
            return parseStandardDateTime(dateTimeString);
        }
        return NaturalDate.getNaturalDate(naturalDateRegExp);
    }

    /**
     * Parses a given standard date time string into a LocalDateTimeObject. This custom parse
     * datetime method can parse a wider variety of date and date time formats, such as with different
     * deliminators, ordering, and int padding. The parser will try to detect year or day patterns
     * so that the closest match can be made. However, in instances of ambiguous dates like 10/10/10,
     * the parser will assume a default format of dd/MM/yyyy. If no time is provided, the time
     * will be automatically set to the start of the day. If no year is provided, the year will
     * be automatically set the current year. If no month is provided, the month will be
     * automatically set to the current month. If no day is provided, the day will be
     * automatically set to the current day.
     *
     * @param dateTimeString The datetime string to be parsed into a datetime object.
     * @return The datetime object parsed from the date time string.
     * @throws DukeException If the datetime string could not be parsed due to unsupported or
     *                       invalid datetime format.
     */
    private static LocalDateTime parseStandardDateTime(String dateTimeString) throws DukeException {
        for (String regexp : DATETIME_FORMATS_REGEXP) {
            if (dateTimeString.toLowerCase().matches(regexp)) {
                String dateTimeParseFormat = DATETIME_FORMATS.get(regexp);
                DateTimeFormatter dateTimeFormatter = new DateTimeFormatterBuilder()
                        .parseCaseInsensitive()
                        .appendPattern(dateTimeParseFormat)
                        .parseDefaulting(ChronoField.YEAR_OF_ERA, currentDate.getYear())
                        .parseDefaulting(ChronoField.MONTH_OF_YEAR, currentDate.getMonthValue())
                        .parseDefaulting(ChronoField.DAY_OF_MONTH, currentDate.getDayOfMonth())
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

    private static void getDateFormatRegex(Map<String, String> dateFormats) {
        DELIMINATORS.forEach(d -> {
            new NumericalDateFormat(d, dateFormats).addNumericalDateFormats();
            new TextDateFormat(d, dateFormats).addTextDateFormats();
        });
    }

    private static LinkedHashMap<String, String> supportedFormats(Map<String, String> dateFormats) {
        LinkedHashMap<String, String> appendedDateTime = new LinkedHashMap<>();
        // first append the datetime and date formats
        dateFormats.forEach((dateFormatRegex, dateFormatSequence) -> {
            // append the supported time formats to each supported date format
            appendTimeFormats(dateFormatRegex, dateFormatSequence, appendedDateTime);
            // add the start and end anchors to the date format regex
            dateFormatRegex = encloseRegex(dateFormatRegex);
            appendedDateTime.put(dateFormatRegex, dateFormatSequence);
        });

        // then append only the time formats (so enable support for time-no-date formats)
        TIME_FORMAT_SUPPORTED.forEach((timeFormatRegex, timeFormatSequence) -> {
            appendedDateTime.put(timeFormatRegex, timeFormatSequence);
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
