package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;

/**
 * Represents a task that need to be done before a specific date.
 */
public class Deadline extends Task {

    public static final String DEADLINE_REP = "D";
    protected LocalDate deadline;

    /**
     * Constructs a new Deadline.
     *
     * @param content Description of the Deadline.
     * @param deadline Date of the deadline.
     * @throws DukeException If the date is invalid.
     */
    public Deadline(String content, String deadline) throws DukeException {
        super(content);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new DukeException("You need to input in yyyy-mm-dd format!");
        }
    }

    /**
     * Returns a String representation of the Deadline.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[" + DEADLINE_REP + "]" + super.toString()
                + " (by: " + this.deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns a String representation of the Deadline for storage.
     *
     * @return String representation of the Deadline for storage.
     */
    @Override
    public String toStorage() {
        return DEADLINE_REP + super.toStorage() + Task.SEPARATOR + this.deadline;
    }
}
