package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import duke.exception.DukeException;

/**
 * Custom date time parser to handle more date time formats and natural dates.
 */
public class DateTimeParse {
    private static final Map<String, String> DATETIME_FORMAT_REGEXPS = initialiseDateTimeFormatMap();
    // todo refactor into enum
    private static String[] noDeliminator;
    private static String[] dashDeliminator;
    private static String[] slashDeliminator;
    private static String[] dotDeliminator;
    private static String[] whiteSpaceDeliminator;
    private static final String timeRegex = "(([0-1]?[0-9]|2[0-3]):[0-5][0-9])";

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
        noDeliminator = new String[]{"", ""};
        dashDeliminator = new String[]{"-", "-"};
        slashDeliminator = new String[]{"\\/", "/"};
        dotDeliminator = new String[]{"\\.", "."};
        whiteSpaceDeliminator = new String[]{"\\s", " "};

        Map<String, String> datetimeFormatRegex = new LinkedHashMap<>();

        // regex ordered from most to least specific so that the best match can be taken
        // populate all date formats first
        getDateFormatRegex(datetimeFormatRegex);
        // then append each date format with time formats
        return appendTimeFormats(datetimeFormatRegex);
    }

    private static void getDateFormatRegex(Map<String, String> dateFormats) {
        get8DigitDateFormatRegex(dateFormats);
        get6DigitDateFormatRegex(dateFormats);
        // todo get7DigitDateFormatRegex
        // todo get5DigitDateFormatRegex
        // todo get4DigitDateFormatRegex
    }

    private static LinkedHashMap<String, String> appendTimeFormats(Map<String, String> dateFormats) {
        LinkedHashMap<String, String> appendedDateTime = new LinkedHashMap<>();
        dateFormats.forEach((dateFormatRegex, dateFormatParsed) -> {
            String dateTimeRegex = "^" + dateFormatRegex + "\\s" + timeRegex + "$";
            String dateTimeFormatParse = dateFormatParsed + " HH:mm";
            appendedDateTime.put(dateTimeRegex, dateTimeFormatParse);

            dateFormatRegex = "^" + dateFormatRegex + "$";
            appendedDateTime.put(dateFormatRegex, dateFormatParsed);
        });
        return appendedDateTime;
    }

    private static void get8DigitDateFormatRegex(Map<String, String> dateFormats) {
        // regex ordered from most to least specific so that the best match can be taken
        List<String[]> deliminators = Arrays.asList(noDeliminator, dashDeliminator, slashDeliminator,
                dotDeliminator, whiteSpaceDeliminator);
        deliminators.forEach(d -> construct8DigitDateFormatRegex(dateFormats, d[0], d[1]));
    }

    private static void get6DigitDateFormatRegex(Map<String, String> dateFormats) {
        // regex ordered from most to least specific so that the best match can be taken
        List<String[]> deliminators = Arrays.asList(noDeliminator, dashDeliminator, slashDeliminator,
                dotDeliminator, whiteSpaceDeliminator);
        deliminators.forEach(d -> construct6DigitDateFormatRegex(dateFormats, d[0], d[1]));
    }

    private static void construct8DigitDateFormatRegex(Map<String, String> dateFormats, String regexDeliminator,
                                                       String parseDeliminator) {
        String year = "yyyy";
        String month = "MM";
        String day = "dd";
        String fourDigitYear = "((19|20)\\d{2})";
        String twoDigitDayNotMonth = "(1[3-9]|2\\d|3[0,1])";
        String twoDigit = "(\\d{2})";

        // add yyyy{deliminator}dd{deliminator}MM
        String regexYearDayMonth = fourDigitYear + regexDeliminator + twoDigitDayNotMonth
                + regexDeliminator + twoDigit;
        String parsedYearDayMonth = year + parseDeliminator + day + parseDeliminator + month;
        dateFormats.put(regexYearDayMonth, parsedYearDayMonth);

        // add yyyy{deliminator}__{deliminator}__ (yyyy{deliminator}MM{deliminator}dd)
        String regexYearMonthDay = fourDigitYear + regexDeliminator + twoDigit + regexDeliminator + twoDigit;
        String parsedYearMonthDay = year + parseDeliminator + month + parseDeliminator + day;
        dateFormats.put(regexYearMonthDay, parsedYearMonthDay);

        // add MM{deliminator}dd{deliminator}yyyy
        String regexMonthDayYear = twoDigit + regexDeliminator + twoDigitDayNotMonth + regexDeliminator + fourDigitYear;
        String parsedMonthDayYear = month + parseDeliminator + day + parseDeliminator + year;
        dateFormats.put(regexMonthDayYear, parsedMonthDayYear);

        // add default (dd{deliminator}MM{deliminator}yyyy)
        String regexDefault = twoDigit + regexDeliminator + twoDigit + regexDeliminator + fourDigitYear;
        String parsedDefault = day + parseDeliminator + month + parseDeliminator + year;
        dateFormats.put(regexDefault, parsedDefault);
    }

    private static void construct6DigitDateFormatRegex(Map<String, String> dateFormats, String regexDeliminator,
                                                       String parseDeliminator) {
        String year = "yy";
        String month = "MM";
        String day = "dd";
        String twoDigitNotMonth = "(1[3-9]|2\\d|3[0,1])";
        String twoDigitMustYear = "(3[2-9]|[4-9]\\d{1})";
        String twoDigit = "(\\d{2})";
        String fourDigitYear = "((19|20)\\d{2})";

        // add yyyy{deliminator}_{deliminator}_ (yyyy{deliminator}M{deliminator}d) [single digit cannot
        // differentiate between month and day]
        String regexY4MD = fourDigitYear + regexDeliminator + "(\\d{1})" + regexDeliminator + "(\\d{1})";
        String parsedY4MD = "yyyy" + parseDeliminator + "M" + parseDeliminator + "d";
        dateFormats.put(regexY4MD, parsedY4MD);

        // add _{deliminator}_{deliminator}yyyy (d{deliminator}M{deliminator}yyyy) [single digit cannot
        // differentiate between month and day]
        String regexDayMonthYear4 = "(\\d{1})" + regexDeliminator + "(\\d{1})" + regexDeliminator + fourDigitYear;
        String parsedDayMonthYear4 = "d" + parseDeliminator + "M" + parseDeliminator + "yyyy";
        dateFormats.put(regexDayMonthYear4, parsedDayMonthYear4);

        // add yy{deliminator}dd{deliminator}MM
        String regexYearDayMonth = twoDigitMustYear + regexDeliminator + twoDigitNotMonth + regexDeliminator + twoDigit;
        String parsedYearDayMonth = year + parseDeliminator + day + parseDeliminator + month;
        dateFormats.put(regexYearDayMonth, parsedYearDayMonth);

        // add yy{deliminator}__{deliminator}__ (yy{deliminator}mm{deliminator}dd)
        String regexYearMonthDay = twoDigitMustYear + regexDeliminator + twoDigit + regexDeliminator + twoDigit;
        String parsedYearMonthDay = year + parseDeliminator + month + parseDeliminator + day;
        dateFormats.put(regexYearMonthDay, parsedYearMonthDay);

        // add MM{deliminator}dd{deliminator}yy
        String regexMonthDayYear = twoDigit + regexDeliminator + twoDigitNotMonth + regexDeliminator + twoDigit;
        String parsedMonthDayYear = month + parseDeliminator + day + parseDeliminator + year;
        dateFormats.put(regexMonthDayYear, parsedMonthDayYear);

        // add default (dd{deliminator}MM{deliminator}yy)
        String regexDefault = twoDigit + regexDeliminator + twoDigit + regexDeliminator + twoDigit;
        String parsedDefault = day + parseDeliminator + month + parseDeliminator + year;
        dateFormats.put(regexDefault, parsedDefault);
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
