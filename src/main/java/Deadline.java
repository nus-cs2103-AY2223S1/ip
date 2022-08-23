import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected java.time.LocalDateTime dueDate;

    /**
     * Constructor for a deadline task.
     *
     * @param description Description of the deadline task
     * @param dueDate The due date of the deadlined task
     */
    public Deadline(String description, LocalDateTime dueDate) {
            super(description);
            this.dueDate = dueDate;
    }

    public Deadline(String description, LocalDateTime dueDate, boolean completion) {
        super(description, completion);
        this.dueDate = dueDate;
    }

    @Override
    public String toSaveFormat() {
        return String.format("D | %s | %s | %s", this.isDone ? "Y" : "N",
                this.description.replace("|", "\\|"),
                this.dueDate);
    }

    /**
     * Returns a string representation for the deadline task,
     * prefixed with a [D], followed by the deadline status, and
     * the deadline description.
     *
     * @return The string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                this.dueDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm")));
    }
}
