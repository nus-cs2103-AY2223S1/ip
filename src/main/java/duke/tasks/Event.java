package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;

public class Event extends Task {

    private final LocalDate at;

    public Event(String description, LocalDate at) throws DukeException {
        super(description);
        this.at = at;
    }

    /**
     * Returns a string representation of the object suitable for storage.
     *
     * @return A storage-friendly string representation.
     */
    @Override
    public String encode() {
        return "E | " + super.encode() + " | " + this.at;
    }

    /**
     * Returns a user-friendly string representation of the object.
     *
     * @return A user-friendly string representation.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
