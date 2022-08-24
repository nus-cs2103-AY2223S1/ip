import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime date;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter
            .ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructor for Event class.
     * @param description Description for event
     * @param dateAndTime Date and time of event
     */
    public Event(String description, Boolean isDone, String dateAndTime) {
        super(description);
        if (isDone) {
            super.markAsDone();
        }

        this.date = LocalDateTime.parse(dateAndTime, FORMAT);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                date.format(DateTimeFormatter.ofPattern("hh:mma MMM dd yyyy")));
    }
}
