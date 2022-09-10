package duke.date;

import java.util.Map;

public abstract class DateFormat {
    protected static final String FOUR_CHAR_YEAR = "yyyy";
    protected static final String TWO_CHAR_YEAR = "yy";
    protected static final String FOUR_CHAR_MONTH = "MMMM";
    protected static final String THREE_CHAR_MONTH = "MMM";
    protected static final String TWO_CHAR_MONTH = "MM";
    protected static final String ONE_CHAR_MONTH = "M";
    protected static final String TWO_CHAR_DAY = "dd";
    protected static final String ONE_CHAR_DAY = "d";
    private final String regexDeliminator;
    private final String formatDeliminator;
    private final Map<String, String> dateFormats;

    /**
     * Returns an instance of the date format handled by the program with
     * the specified deliminator.
     *
     * @param deliminator The deliminator that separates the date fields.
     * @param dateFormats The map to insert the regex-date format pairs into.
     */
    public DateFormat(Deliminator deliminator, Map<String, String> dateFormats) {
        regexDeliminator = deliminator.getDeliminatorRegex();
        formatDeliminator = deliminator.getDeliminator();
        this.dateFormats = dateFormats;
    }

    protected void put(String regexExp, String dateTimeFormat) {
        dateFormats.put(regexExp, dateTimeFormat);
    }

    protected String dateRegexDeliminator(String ... regexFields) {
        return String.join(regexDeliminator, regexFields);
    }

    protected String dateFormatDeliminator(String ... dateFields) {
        return String.join(formatDeliminator, dateFields);
    }
}
