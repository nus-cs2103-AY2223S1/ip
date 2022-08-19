import java.time.LocalDateTime;

/**
 * A task that start at a specific date/time and ends at a specific date/time.
 */
public class Event extends Task {
    private final LocalDateTime datetime;

    public Event(String description, LocalDateTime datetime) {
        super(description);
        this.datetime = datetime;
    }

    public Event(String description, String datetime) {
        this(description, LocalDateTime.parse(datetime, Task.DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(),
                Task.DATE_TIME_FORMATTER.format(datetime)
        );
    }
}
