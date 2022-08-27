import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;

    /**
     * Constructs an Event task.
     *
     * @param description Description of the Event task.
     * @param at Date the Event task occurs at.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    public String formatTime(LocalDate time) {
        return time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Shows the Event task description and the date it occurs at.
     *
     * @return String with the Event task description and the date it occurs at.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formatTime(at) + ")";
    }
}
