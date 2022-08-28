package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JamieTime {
    public static final DateTimeFormatter INPUT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    public static final DateTimeFormatter OUTPUT_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("EEE, d MMMM yyyy, h:mma");

    public static String reformatDateTime(String input) {
        return LocalDateTime.parse(input, INPUT_DATE_TIME_FORMAT)
                .format(OUTPUT_DATE_TIME_FORMAT);
    }

    public static String undoReformatDateTime(String input) {
        return LocalDateTime.parse(input, OUTPUT_DATE_TIME_FORMAT)
                .format(INPUT_DATE_TIME_FORMAT);
    }

}
