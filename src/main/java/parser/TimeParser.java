package parser;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;

public class TimeParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d/MM/yyyy[ HHmm]");
    private static final DateTimeFormatter PRINT_FORMAT = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");

    public LocalDateTime createDeadline(String time) throws DateTimeParseException {
        TemporalAccessor temporalAccessor = FORMATTER.parseBest(time, LocalDateTime::from, LocalDate::from);
        if (temporalAccessor instanceof LocalDateTime) {
            return (LocalDateTime) temporalAccessor;
        } else {
            return ((LocalDate) temporalAccessor).atStartOfDay();
        }
    }

    public String formatDeadline(LocalDateTime deadline) {
        return deadline.format(PRINT_FORMAT);
    }

    public String formatDeadline(String deadline) {
        return createDeadline(deadline).format(PRINT_FORMAT);
    }
}

