package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline class which is a subclass of Event.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline Class.
     *
     * @param description Description of deadline task.
     * @param by Date of event, of format yyyy-mm-dd.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter valid date format (yyyy-mm-dd)");
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public String saveTaskAsString() {
        String status = this.isDone ? "1" : "0";
        return String.format("D | %s | %s | %s", status, this.description, this.by);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}