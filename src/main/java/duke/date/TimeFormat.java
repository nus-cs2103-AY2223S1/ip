package duke.date;

import java.util.LinkedHashMap;

/**
 * Represents the time formats supported by the program.
 */
public class TimeFormat {
    private static final String AM_PM_REGEX = "(AM|PM)";
    private static final String PADDED_12_HOUR_REGEX = "(?i)(1[0-2]|0[1-9])";
    private static final String NON_PADDED_12_HOUR_REGEX = "(?i)(1[0-2]|0?[1-9])";
    private static final String MINUTE_REGEX = "[0-5][0-9]";
    private static final String PADDED_24_HOUR_REGEX = "([0-1][0-9]|2[0-3])";
    private static final String NON_PADDED_24_HOUR_REGEX = "([0-1]?[0-9]|2[0-3])";
    // stores the supported time formats in a linked hash table (order of regex check is important)
    private static final LinkedHashMap<String, String> TIME_FORMAT_SUPPORTED = new LinkedHashMap<>();

    /**
     * Returns a LinkedHashMap of the supported time formats.
     *
     * @return LinkedHashMap of the supported time formats, where the key is the regex of
     *         the supported time format, and the value is the time format sequence.
     */
    public static LinkedHashMap<String, String> getTimeFormats() {
        DateTimeParse.DELIMINATORS.forEach((Deliminator deliminator) -> {
            String deliminatorRegex = deliminator.getDeliminatorRegex();
            String deliminatorFormat = deliminator.getDeliminator();
            add12HourTimeFormats(deliminatorRegex, deliminatorFormat);
            add24HourTimeFormats(deliminatorRegex, deliminatorFormat);
        });
        return TIME_FORMAT_SUPPORTED;
    }

    private static void add12HourTimeFormats(String deliminatorRegex, String deliminatorFormat) {
        // Padded 12-hour time format with no space between the AM/PM
        String padded12HourRegexNoSpace = PADDED_12_HOUR_REGEX + deliminatorRegex
                + MINUTE_REGEX + AM_PM_REGEX;
        String padded12HourFormatNoSpace = "hh" + deliminatorFormat + "mma";

        // Padded 12-hour time format with space between the AM/PM
        String padded12HourRegexWithSpace = PADDED_12_HOUR_REGEX + deliminatorRegex
                + MINUTE_REGEX + " " + AM_PM_REGEX;
        String padded12HourFormatWithSpace = "hh" + deliminatorFormat + "mm a";

        // Non-Padded 12-hour time format with no space between the AM/PM
        String nonPadded12HourRegexNoSpace = NON_PADDED_12_HOUR_REGEX + deliminatorRegex
                + MINUTE_REGEX + AM_PM_REGEX;
        String nonPadded12HourFormatNoSpace = "h" + deliminatorFormat + "mma";

        // Non-Padded 12-hour time format with space between the AM/PM
        String nonPadded12HourRegexWithSpace = NON_PADDED_12_HOUR_REGEX
                + deliminatorRegex + MINUTE_REGEX + " " + AM_PM_REGEX;
        String nonPadded12HourFormatWithSpace = "h" + deliminatorFormat + "mm a";

        // Non-Padded 12-hour time format with no minutes and with space between the AM/PM
        String nonPadded12HourNoMinRegexWithSpace = NON_PADDED_12_HOUR_REGEX + " " + AM_PM_REGEX;
        String nonPadded12HourNoMinFormatWithSpace = "h a";

        // Non-Padded 12-hour time format with no minutes and with no space between the AM/PM
        String nonPadded12HourNoMinRegexWithNoSpace = NON_PADDED_12_HOUR_REGEX + AM_PM_REGEX;
        String nonPadded12HourNoMinFormatWithNoSpace = "ha";

        TIME_FORMAT_SUPPORTED.put(padded12HourRegexNoSpace, padded12HourFormatNoSpace);
        TIME_FORMAT_SUPPORTED.put(padded12HourRegexWithSpace, padded12HourFormatWithSpace);
        TIME_FORMAT_SUPPORTED.put(nonPadded12HourRegexNoSpace, nonPadded12HourFormatNoSpace);
        TIME_FORMAT_SUPPORTED.put(nonPadded12HourRegexWithSpace, nonPadded12HourFormatWithSpace);
        TIME_FORMAT_SUPPORTED.put(nonPadded12HourNoMinRegexWithSpace, nonPadded12HourNoMinFormatWithSpace);
        TIME_FORMAT_SUPPORTED.put(nonPadded12HourNoMinRegexWithNoSpace, nonPadded12HourNoMinFormatWithNoSpace);
    }

    private static void add24HourTimeFormats(String deliminatorRegex, String deliminatorFormat) {
        String padded24HourRegex = PADDED_24_HOUR_REGEX + deliminatorRegex + MINUTE_REGEX;
        String padded24HourFormat = "HH" + deliminatorFormat + "mm";
        String nonPadded24HourRegex = NON_PADDED_24_HOUR_REGEX + deliminatorRegex + MINUTE_REGEX;
        String nonPadded24HourFormat = "H" + deliminatorFormat + "mm";

        TIME_FORMAT_SUPPORTED.put(padded24HourRegex, padded24HourFormat);
        TIME_FORMAT_SUPPORTED.put(nonPadded24HourRegex, nonPadded24HourFormat);
    }
}
