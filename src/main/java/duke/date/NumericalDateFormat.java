package duke.date;

import java.util.Map;

/**
 * Handles numerical date formats. Tries to match the string to the closest format by
 * detecting trying to guess if a field is a day, month, or year field. The order
 * in which these formats are inserted into the linked hashtable is important to ensure
 * that the closest regex and thus, date format match can be given.
 */
public class NumericalDateFormat {
    private static final String FOUR_DIGIT_YEAR_REGEX = "((19|20)\\d{2})";
    private static final String TWO_DIGIT_NOT_MONTH_REGEX = "(1[3-9]|2\\d|3[0,1])";
    private static final String TWO_DIGIT_MUST_YEAR_REGEX = "(3[2-9]|[4-9]\\d{1})";
    private static final String TWO_DIGIT_REGEX = "(\\d{2})";
    private static final String ONE_DIGIT_REGEX = "(\\d{1})";
    private static final String FOUR_CHAR_YEAR = "yyyy";
    private static final String TWO_CHAR_YEAR = "yy";
    private static final String TWO_CHAR_MONTH = "MM";
    private static final String ONE_CHAR_MONTH = "M";
    private static final String TWO_CHAR_DAY = "dd";
    private static final String ONE_CHAR_DAY = "d";
    private final String regexDeliminator;
    private final String formatDeliminator;

    /**
     * Returns an instance of the numerical date format handled by the program with
     * the specified deliminator.
     *
     * @param deliminator The deliminator that separates the date fields.
     */
    public NumericalDateFormat(Deliminator deliminator) {
        regexDeliminator = deliminator.getDeliminatorRegex();
        formatDeliminator = deliminator.getDeliminator();
    }

    /**
     * Adds the regex and numerical date format pairs handled by
     * the program into the specified map.
     *
     * @param dateFormats The map to insert the regex-date format pairs into.
     */
    public void addNumericalDateFormats(Map<String, String> dateFormats) {
        add8DigitDateFormat(dateFormats);
        add6DigitDateFormat(dateFormats);
        add7DigitDateFormat(dateFormats);
        add4DigitDateFormat(dateFormats);
    }

    /**
     * Handles 8 digit numerical dates- assumes that year has 4 digits, month has 2 digits, and
     * day has 2 digits.
     *
     * @param dateFormats The map to insert the regex-date format pairs into.
     */
    private void add8DigitDateFormat(Map<String, String> dateFormats) {
        // add yyyy{deliminator}dd{deliminator}MM
        String regexYearDayMonth = dateRegexDeliminator(FOUR_DIGIT_YEAR_REGEX,
                TWO_DIGIT_NOT_MONTH_REGEX, TWO_DIGIT_REGEX);
        String parsedYearDayMonth = dateFormatDeliminator(FOUR_CHAR_YEAR, TWO_CHAR_DAY, TWO_CHAR_MONTH);
        dateFormats.put(regexYearDayMonth, parsedYearDayMonth);

        // add yyyy{deliminator}__{deliminator}__ (yyyy{deliminator}MM{deliminator}dd)
        String regexYearMonthDay = dateRegexDeliminator(FOUR_DIGIT_YEAR_REGEX, TWO_DIGIT_REGEX,
                TWO_DIGIT_REGEX);
        String parsedYearMonthDay = dateFormatDeliminator(FOUR_CHAR_YEAR, TWO_CHAR_MONTH, TWO_CHAR_DAY);
        dateFormats.put(regexYearMonthDay, parsedYearMonthDay);

        // add MM{deliminator}dd{deliminator}yyyy
        String regexMonthDayYear = dateRegexDeliminator(TWO_DIGIT_REGEX, TWO_DIGIT_NOT_MONTH_REGEX,
                FOUR_DIGIT_YEAR_REGEX);
        String parsedMonthDayYear = dateFormatDeliminator(TWO_CHAR_MONTH, TWO_CHAR_DAY, FOUR_CHAR_YEAR);
        dateFormats.put(regexMonthDayYear, parsedMonthDayYear);

        // add default (dd{deliminator}MM{deliminator}yyyy)
        String regexDefault = dateRegexDeliminator(TWO_DIGIT_REGEX, TWO_DIGIT_REGEX, FOUR_DIGIT_YEAR_REGEX);
        String parsedDefault = dateFormatDeliminator(TWO_CHAR_DAY, TWO_CHAR_MONTH, TWO_CHAR_YEAR);
        dateFormats.put(regexDefault, parsedDefault);
    }

    /**
     * Handles 6 digit date formats. Assumes that year can be either 2 or 4 digits, while
     * month and date can be either 1 or 2 digits.
     *
     * @param dateFormats The map to insert the regex-date format pairs into.
     */
    private void add6DigitDateFormat(Map<String, String> dateFormats) {
        // add yyyy{deliminator}_{deliminator}_ (yyyy{deliminator}M{deliminator}d) [single digit cannot
        // differentiate between month and day]
        String regexY4MD = dateRegexDeliminator(FOUR_DIGIT_YEAR_REGEX, ONE_DIGIT_REGEX, ONE_DIGIT_REGEX);
        String parsedY4MD = dateFormatDeliminator(FOUR_CHAR_YEAR, ONE_CHAR_MONTH, ONE_CHAR_DAY);
        dateFormats.put(regexY4MD, parsedY4MD);

        // add _{deliminator}_{deliminator}yyyy (d{deliminator}M{deliminator}yyyy) [single digit cannot
        // differentiate between month and day]
        String regexDayMonthYear4 = dateRegexDeliminator(ONE_DIGIT_REGEX, ONE_DIGIT_REGEX, FOUR_DIGIT_YEAR_REGEX);
        String parsedDayMonthYear4 = dateFormatDeliminator(ONE_CHAR_DAY, ONE_CHAR_MONTH, FOUR_CHAR_YEAR);
        dateFormats.put(regexDayMonthYear4, parsedDayMonthYear4);

        // add yy{deliminator}dd{deliminator}MM
        String regexYearDayMonth = dateRegexDeliminator(TWO_DIGIT_MUST_YEAR_REGEX,
                TWO_DIGIT_NOT_MONTH_REGEX, TWO_DIGIT_REGEX);
        String parsedYearDayMonth = dateFormatDeliminator(TWO_CHAR_YEAR, TWO_CHAR_DAY, TWO_CHAR_MONTH);
        dateFormats.put(regexYearDayMonth, parsedYearDayMonth);

        // add yy{deliminator}__{deliminator}__ (yy{deliminator}mm{deliminator}dd)
        String regexYearMonthDay = dateRegexDeliminator(TWO_DIGIT_MUST_YEAR_REGEX, TWO_DIGIT_REGEX, TWO_DIGIT_REGEX);
        String parsedYearMonthDay = dateFormatDeliminator(TWO_CHAR_YEAR, TWO_CHAR_MONTH, TWO_CHAR_DAY);
        dateFormats.put(regexYearMonthDay, parsedYearMonthDay);

        // add MM{deliminator}dd{deliminator}yy
        String regexMonthDayYear = dateRegexDeliminator(TWO_DIGIT_REGEX, TWO_DIGIT_NOT_MONTH_REGEX, TWO_DIGIT_REGEX);
        String parsedMonthDayYear = dateFormatDeliminator(TWO_CHAR_MONTH, TWO_CHAR_DAY, TWO_CHAR_YEAR);
        dateFormats.put(regexMonthDayYear, parsedMonthDayYear);

        // add default (dd{deliminator}MM{deliminator}yy)
        String regexDefault = dateRegexDeliminator(TWO_DIGIT_REGEX, TWO_DIGIT_REGEX, TWO_DIGIT_REGEX);
        String parsedDefault = dateFormatDeliminator(TWO_CHAR_DAY, TWO_CHAR_MONTH, TWO_CHAR_YEAR);
        dateFormats.put(regexDefault, parsedDefault);
    }

    /**
     * Handles 7 digit date formats. Assumes that year must be 4 digits (since there are no
     * valid days or months with 3 digits).
     * @param dateFormats The map to insert the regex-date format pairs into.
     */
    private void add7DigitDateFormat(Map<String, String> dateFormats) {
        // yyyy{deliminator}dd{deliminator}M (provided dd cannot be a MM)
        String regexYearDayMonth = dateRegexDeliminator(FOUR_DIGIT_YEAR_REGEX,
                TWO_DIGIT_NOT_MONTH_REGEX, ONE_DIGIT_REGEX);
        String parsedYearDayMonth = dateFormatDeliminator(FOUR_CHAR_YEAR, TWO_CHAR_DAY, ONE_CHAR_MONTH);
        dateFormats.put(regexYearDayMonth, parsedYearDayMonth);

        // else fall back on yyyy{deliminator}mm{deliminator}d (if can't tell if a mm/d)
        String regexYearMonthDay = dateRegexDeliminator(FOUR_DIGIT_YEAR_REGEX,
                TWO_DIGIT_REGEX, ONE_DIGIT_REGEX);
        String parsedYearDay2Month = dateFormatDeliminator(FOUR_CHAR_YEAR, TWO_CHAR_MONTH, ONE_CHAR_DAY);
        dateFormats.put(regexYearMonthDay, parsedYearDay2Month);

        // dd{deliminator}m{deliminator}yyyy (provided dd cannot be a mm)
        String regexDay1MonthYear = dateRegexDeliminator(TWO_DIGIT_NOT_MONTH_REGEX, ONE_DIGIT_REGEX,
                FOUR_DIGIT_YEAR_REGEX);
        String parsedDay1MonthYear = dateFormatDeliminator(TWO_CHAR_DAY, ONE_CHAR_MONTH, FOUR_CHAR_YEAR);
        dateFormats.put(regexDay1MonthYear, parsedDay1MonthYear);

        // else fall back on d{deliminator}mm{deliminator}yyyy (if can't tell if a d/mm)
        String regexDay2MonthYear = dateRegexDeliminator(ONE_DIGIT_REGEX, TWO_DIGIT_REGEX, FOUR_DIGIT_YEAR_REGEX);
        String parsedDay2MonthYear = dateFormatDeliminator(ONE_CHAR_DAY, TWO_CHAR_MONTH, FOUR_CHAR_YEAR);
        dateFormats.put(regexDay2MonthYear, parsedDay2MonthYear);
    }

    /**
     * Handles 4 digit date formats, assumes the year is 2 digits, and the day and month
     * are both 1 digits each.
     *
     * @param dateFormats The map to insert the regex-date format pairs into.
     */
    private void add4DigitDateFormat(Map<String, String> dateFormats) {
        // yy{deliminator}M{deliminator}d (2 digit assumed to be be year)
        String regexYearMonthDay = dateRegexDeliminator(TWO_DIGIT_REGEX, ONE_DIGIT_REGEX, ONE_DIGIT_REGEX);
        String parsedYearMonthDay = dateFormatDeliminator(TWO_CHAR_YEAR, ONE_CHAR_MONTH, ONE_CHAR_DAY);
        dateFormats.put(regexYearMonthDay, parsedYearMonthDay);

        // d{deliminator}M{deliminator}yy (must be year)
        String regexDayMonthYear = dateRegexDeliminator(ONE_DIGIT_REGEX, ONE_DIGIT_REGEX, TWO_DIGIT_REGEX);
        String parsedDayMonthYear = dateFormatDeliminator(ONE_CHAR_DAY, ONE_CHAR_MONTH, TWO_CHAR_YEAR);
        dateFormats.put(regexDayMonthYear, parsedDayMonthYear);
    }

    private String dateRegexDeliminator(String regexField1, String regexField2, String regexField3) {
        return regexField1 + regexDeliminator + regexField2 + regexDeliminator + regexField3;
    }

    private String dateFormatDeliminator(String dateField1, String dateField2, String dateField3) {
        return dateField1 + formatDeliminator + dateField2 + formatDeliminator + dateField3;
    }
}
