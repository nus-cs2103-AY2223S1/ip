package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Creates a new deadline task with the given description and deadline.
     *
     * @param description Description of the task.
     * @param by          Deadline in yyyy-mm-dd format.
     * @throws DukeException If format of deadline is wrong.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please provide a date in yyyy-mm-dd format.");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
