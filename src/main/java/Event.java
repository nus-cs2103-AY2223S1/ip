import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Event extends Task {
    private static final String DATE_TIME_SEPARATOR = " /at ";
    private static final DateTimeFormatter INPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("d/M/yy[ HHmm]");
    private static final DateTimeFormatter OUTPUT_DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy[ h:mma]");

    private final TemporalAccessor dateTime;

    private Event(String description, TemporalAccessor dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public static Event fromUserInput(String userInput) throws InvalidTaskFormatException {
        String[] splitUserInput = userInput.split(DATE_TIME_SEPARATOR, 2);
        if (splitUserInput.length < 2) {
            throw new InvalidTaskFormatException("No time was provided for this event.");
        }
        TemporalAccessor dateTime = INPUT_DATE_TIME_FORMATTER.parseBest(splitUserInput[1],
                LocalDateTime::from, LocalDate::from);
        return new Event(splitUserInput[0], dateTime);
    }

    @Override
    public String toString() {
        return String.format("(E) %s (at: %s)", super.toString(),
                OUTPUT_DATE_TIME_FORMATTER.format(this.dateTime));
    }
}
