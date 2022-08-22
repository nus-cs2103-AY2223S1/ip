import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate at;
    /**
     * Constructor for a event instance.
     *
     * @param description the description of the event
     * @param at the time of the event
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Invalid Date Format, please input it as YYYY-MM-DD");
        }
    }

    /**
     * String representation of the event.
     *
     * @return String representing this event
     */
    @Override
    public String toString() {
        return "[E]" +
                super.toString() +
                " (at: " +
                this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) +
                ")";
    }

    /**
     * Gets the deadline of the Task, if any.
     *
     * @return A LocalDate representing the Task's deadline if it exists
     *         null if no such deadline exists
     */
    @Override
    public LocalDate getDeadline() {
        return this.at;
    }
}
