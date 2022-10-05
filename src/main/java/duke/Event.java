package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An Event class which is a subclass of Task.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructor for Event Class.
     *
     * @param description Description of event task.
     * @param at Date of event, of format yyyy-mm-dd.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
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
        return String.format("E | %s | %s | %s", status, this.description, this.at);
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
