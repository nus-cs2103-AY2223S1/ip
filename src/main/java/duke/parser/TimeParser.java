package duke.parser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class TimeParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy[ HHmm]");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    /**
     * Creates time representation of deadline from given string input.
     *
     * @param time string representation of time
     * @return time in LocalDateTime format
     * @throws DateTimeParseException if given string representation follows invalid format
     */
    public LocalDateTime createDeadline(String time) throws DateTimeParseException {
        TemporalAccessor temporalAccessor = FORMATTER.parseBest(time, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        } else {
            return ((LocalDate) temporalAccessor).atStartOfDay();
        }
    }

    /**
     * Returns string representation of deadline given a LocalDateTime representation.
     *
     * @param deadline LocalDateTime representation of deadline
     * @return String representation with standard time format
     */
    public String formatDeadline(LocalDateTime deadline) {
        return deadline.format(PRINT_FORMAT);
    }

    /**
     * Returns formatted string given a string representation of deadline.
     *
     * @param deadline unformatted string representation of deadline
     * @return formatted string representation of deadline
     */
    public String formatDeadline(String deadline) {
        return createDeadline(deadline).format(PRINT_FORMAT);
    }
}

