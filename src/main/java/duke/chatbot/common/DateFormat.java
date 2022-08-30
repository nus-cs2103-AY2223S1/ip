package duke.chatbot.common;

import java.time.format.DateTimeFormatter;

/**
 * Contains the string formatter for date and time related strings used in the
 * application.
 * @author Jordan Quah Shao Xuan
 */
public class DateFormat {
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("HHmm");
}
