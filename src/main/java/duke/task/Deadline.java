package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Encapsulation of a deadline.
 *
 * @author Sun Ruoxin
 */
public class Deadline extends Task {
    /** The date of the deadline. */
    protected LocalDate by;

    /**
     * Class constructor.
     *
     * @param description the description of the deadline
     * @param by the date of the deadline
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException exception) {
            throw new DukeException("Please enter the date in the format of yyyy-mm-dd");
        }
    }

    /**
     * Class constructor with specified status.
     *
     * @param description the description of the deadline
     * @param isDone the status of the deadline
     * @param by the date of the deadline
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the date of the deadline.
     *
     * @return the date of the deadline
     */
    public LocalDate getBy() {
        return by;
    }

    /**
     * Returns the type of the task represented by a character.
     *
     * @return "D" for deadline
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Returns the string representation of the deadline.
     *
     * @return the string representation of the deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
