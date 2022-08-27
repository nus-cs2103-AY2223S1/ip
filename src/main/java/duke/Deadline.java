package duke;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task that can be stored by Duke.
 * Deadline tasks must be specified with a date to be done by.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for a deadline.
     *
     * @param description description of the task.
     * @param by the date specified to complete the deadline task by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Converts the deadline to a String
     *
     * @return the string representation of a deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM-d-yyyy")) + ")";
    }

    /**
     * Converts the deadline task to its string representation.
     *
     * @return String representation of the deadline.
     */
    @Override
    public String getSaveString() {
        return "D | " + (isDone ? "1 | " : "0 | " + this.description + " | " + this.by);
    }
}
