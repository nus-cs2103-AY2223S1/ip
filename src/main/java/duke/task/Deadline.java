package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A deadline is a task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    /** The due date associated with the deadline task, by which the task should be completed */
    protected java.time.LocalDateTime dueDate;

    /**
     * Constructs a deadline task.
     *
     * @param description Description of the deadline task.
     * @param dueDate The due date of the deadlined task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Constructs a deadline task with a specified completion status.
     *
     * @param description Description of the deadline task.
     * @param dueDate The due date of the deadlined task.
     * @param completion Whether the Deadline task has been completed.
     */
    public Deadline(String description, LocalDateTime dueDate, boolean completion) {
        super(description, completion);
        this.dueDate = dueDate;
    }

    /**
     * Returns a String representation of the due date datetime object associated to the
     * deadline task in EEEE, dd MMM yyyy HH:mm format.
     *
     * @return String representation of the due date in EEEE, dd MMM yyyy HH:mm format.
     */
    public String getDueDatetimeString() {
        DateTimeFormatter dayDateTimeFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy HH:mm");
        return dueDate.format(dayDateTimeFormatter);
    }

    /**
     * Parses the Deadline into a savable string format, ready to be written to the hard disk.
     *
     * @return Savable string representation of the Deadline Task.
     */
    @Override
    public String toSaveFormat() {
        String savableCompletion = this.isDone ? "Y" : "N";
        // escape instances of deliminator in task description
        String escapedDescription = description.replace("|", "\\|");
        return String.format("D | %s | %s | %s", savableCompletion, escapedDescription, dueDate);
    }

    /**
     * Returns a string representation for the deadline task, prefixed with a [D],
     * followed by the deadline status, and the deadline description.
     *
     * @return The string representation of the deadline task.
     */
    @Override
    public String toString() {
        String completionDescription = super.toString();
        String formattedDueDate = getDueDatetimeString();
        return String.format("[D]%s (by: %s)", completionDescription, formattedDueDate);
    }
}
