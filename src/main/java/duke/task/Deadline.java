package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    protected java.time.LocalDateTime dueDate;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the deadline task
     * @param dueDate The due date of the deadlined task
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a deadline task with a specified completion status.
     *
     * @param description Description of the deadline task
     * @param dueDate The due date of the deadlined task
     * @param completion Whether the Deadline task has been completed
     */
    public Deadline(String description, LocalDateTime dueDate, boolean completion) {
        super(description, completion);
        this.dueDate = dueDate;
    }

    public String getDueDatetimeString() {
        return dueDate.format(DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm"));
    }

    /**
     * Parses the Deadline into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the Deadline Task
     */
    @Override
    public String toSaveFormat() {
        return String.format("D | %s | %s | %s", this.isDone ? "Y" : "N",
                this.description.replace("|", "\\|"),
                this.dueDate);
    }

    /**
     * Returns a string representation for the deadline task, prefixed with a [D],
     * followed by the deadline status, and the deadline description.
     *
     * @return The string representation of the deadline task
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDueDatetimeString());
    }
}
