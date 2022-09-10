package duke.date;

import java.util.Map;

public class TextDateFormat extends DateFormat {
    private static final String ABBREVIATED_MONTH_REGEX = "(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec)";
    private static final String LONG_MONTH_REGEX = "(january|february|march|april|may|"
        + "june|july|august|september|october|november|december)";
    private static final String DAY_REGEX = "([1-9]|[12][0-9]|3[01])";
    private static final String TWO_DIGIT_DAY_REGEX = "(0?[1-9]|[12][0-9]|3[01])";
    private static final String FOUR_DIGIT_YEAR_REGEX = "((19|20)\\d{2})";
    private static final String TWO_DIGIT_REGEX = "(\\d{2})";

    /**
     * {@inheritDoc}
     */
    public TextDateFormat(Deliminator deliminator, Map<String, String> dateFormats) {
        super(deliminator, dateFormats);
    }

    public void addTextDateFormats() {
        // add abbreviated months
        addTextMonth(ABBREVIATED_MONTH_REGEX, THREE_CHAR_MONTH);
        // add long months
        addTextMonth(LONG_MONTH_REGEX, FOUR_CHAR_MONTH);
    }

    private void addTextMonth(String monthRegex, String monthFormat) {
        // d MMM yyyy
        String regexDayMonthYear = dateRegexDeliminator(DAY_REGEX, monthRegex,
                FOUR_DIGIT_YEAR_REGEX);
        String parsedDayMonthYear = dateFormatDeliminator(ONE_CHAR_DAY, monthFormat, FOUR_CHAR_YEAR);
        put(regexDayMonthYear, parsedDayMonthYear);

        // dd MMM yyyy
        String regexDay2MonthYear = dateRegexDeliminator(TWO_DIGIT_DAY_REGEX, monthRegex,
                FOUR_DIGIT_YEAR_REGEX);
        String parsedDay2MonthYear = dateFormatDeliminator(TWO_CHAR_DAY, monthFormat, FOUR_CHAR_YEAR);
        put(regexDay2MonthYear, parsedDay2MonthYear);

        // MMM d yyyy
        String regexMonthDayYear = dateRegexDeliminator(monthRegex, DAY_REGEX, FOUR_DIGIT_YEAR_REGEX);
        String parsedMonthDayYear = dateFormatDeliminator(monthFormat, ONE_CHAR_DAY, FOUR_CHAR_YEAR);
        put(regexMonthDayYear, parsedMonthDayYear);

        // MMM dd yyyy
        String regexMonthDay2Year = dateRegexDeliminator(monthRegex,
                TWO_DIGIT_DAY_REGEX, FOUR_DIGIT_YEAR_REGEX);
        String parsedMonthDay2Year = dateFormatDeliminator(monthFormat, TWO_CHAR_DAY, FOUR_CHAR_YEAR);
        put(regexMonthDay2Year, parsedMonthDay2Year);

        // d MMM
        String regexDayMonth = dateRegexDeliminator(DAY_REGEX, monthRegex);
        String parsedDayMonth = dateFormatDeliminator(ONE_CHAR_DAY, monthFormat);
        put(regexDayMonth, parsedDayMonth);

        // dd MMM
        String regexDay2Month = dateRegexDeliminator(TWO_DIGIT_DAY_REGEX, monthRegex);
        String parsedDay2Month = dateFormatDeliminator(TWO_CHAR_DAY, monthFormat);
        put(regexDay2Month, parsedDay2Month);

        // MMM yyyy
        String regexMonthYear = dateRegexDeliminator(monthRegex, FOUR_DIGIT_YEAR_REGEX);
        String parsedMonthYear = dateFormatDeliminator(monthFormat, FOUR_CHAR_YEAR);
        put(regexMonthYear, parsedMonthYear);

        // MMM
        put(monthRegex, monthFormat);
    }
}
