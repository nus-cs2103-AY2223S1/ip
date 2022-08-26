package duke.task;

import duke.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a user set task due by a time.
 *
 * @author totsukatomofumi
 */
public class Deadline extends Task {
    /** Time the deadline is due. */
    private LocalDate time;

    /**
     * Constructs a deadline.
     *
     * @param description the description of the deadline.
     * @param time the time the deadline is due.
     */
    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    /**
     * Returns a string representation of the deadline.
     *
     * @return a string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + String.format(" (by: %s)", time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Returns a string representation of the deadline that conforms to how storage
     * parses from the task history file.
     *
     * @return the parsable string representation of the deadline.
     */
    @Override
    public String toData() {
        return "D" + super.toData() + this.time.toString();
    }
}
