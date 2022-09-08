package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * The Deadline class represents a deadline task.
 */
public class Deadline extends Task {

    private String by;
    protected LocalDate byDate;

    /**
     * Constructor for Deadline.
     * @param description Description of the task.
     * @param by When the task has to be done by.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        try {
            LocalDate d1 = LocalDate.parse("2019-12-01");
            byDate = LocalDate.parse(by);
        } catch (DateTimeException e) {
            // Do nothing as the input is not parsable as a Date.
        }
    }

    /**
     * Getter method for by.
     * @return by.
     */
    public String getBy() {
        return this.by;
    }

    /**
     * Returns the string representation of the current object.
     * @return String representation of the object.
     */
    @Override
    public String toString() {
        if (byDate != null) {
            return "[D]" + super.toString() + " (at: " + byDate.toString() + ")";
        } else {
            return "[D]" + super.toString() + " (at: " + by + ")";
        }
    }
}
