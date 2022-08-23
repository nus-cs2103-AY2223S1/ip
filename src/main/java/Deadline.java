import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline Class.
 */
public class Deadline extends Task {
    /** Deadlines have a time to be completed by that is a String. */
    protected LocalDateTime by;

    /**
     * Constructor for Deadline objects.
     * @param description The description of the task.
     * @param by The time by which said task must be completed.
     * @throws DukeException For Duke related exceptions.
     */
    public Deadline(String description, LocalDateTime by) throws DukeException {
        super(description);
        this.by = by;
        if (description.isBlank()) {
            throw new DukeException("The description of deadline task is missing information!");
        }
    }

    /**
     * String representation of a deadline task object.
     * @return Returns the String representation of the current object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) + ")";
    }
}
