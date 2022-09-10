package skyler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that needs to be done before a specific date and time
 */
public class Deadline extends Task {

    protected LocalDateTime deadline;

    /**
     * Creates a deadline object
     *
     * @param description Description of the task.
     * @param deadline Due date of the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
        assert this instanceof Task : "Deadline should be a subtype  of Task";
    }

    /**
     * Formats due date of task
     *
     * @param dateTime LocalDateTime object to be formatted.
     * @return Date and time in the format <code>MMM dd yyyy h:mma</code>.
     */
    public String formatDateTime(LocalDateTime dateTime) {
        return dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy h:mma"));
    }

    @Override
    public String toStringUnformatted() {
        String unformatted = deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("[D]%s (by: %s)", super.toString(), unformatted);
    }

    @Override
    public String toString() {
        String formatted = formatDateTime(deadline);
        return String.format("[D]%s (by: %s)", super.toString(), formatted);
    }
}
