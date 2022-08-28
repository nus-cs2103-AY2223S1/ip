package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * JamieTime class converts DateTime inputs and outputs.
 */
public class JamieTime {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("EEE, d MMMM yyyy, h:mma");

    /**
     * Formats the dateTime String from user or file input.
     *
     * @param input Date and time in the format d/M/yyyy HHmm.
     * @return Date and time for users to read with ease.
     */
    public static String reformatDateTime(String input) {
        return LocalDateTime.parse(input, INPUT_DATE_TIME_FORMAT)
                .format(OUTPUT_DATE_TIME_FORMAT);
    }

    /**
     * Undo the formatting done to a date and time input.
     *
     * @param input Date and time in the format read by users.
     * @return Date and time format to be stored in a text file.
     */
    public static String undoReformatDateTime(String input) {
        return LocalDateTime.parse(input, OUTPUT_DATE_TIME_FORMAT)
                .format(INPUT_DATE_TIME_FORMAT);
    }

}
