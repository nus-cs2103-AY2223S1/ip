package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Encapsulate an Event which is-a Task.
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Constructs an instance of Event which inherits from Task.
     *
     * @param description Event's description.
     * @param at Event's at.
     * @throws DukeException if event's at is empty.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        if (at.equals(" ")) {
            throw new DukeException("The event needs to have specific start time and end time nya.");
        }
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date after /at should be in YYYY-MM-DD Format nya.");
        }

    }

    @Override
    public void updateDate(String updatedAt) throws DukeException {
        this.at = LocalDate.parse(updatedAt);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toStorageString() {
        return "E" + super.toStorageString() + " | " + this.at;
    }

}
