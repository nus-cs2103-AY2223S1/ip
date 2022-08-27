package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final LocalDate by;

    public Deadline(String description, LocalDate by) throws DukeException {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string representation of the object suitable for storage.
     *
     * @return A storage-friendly string representation.
     */
    @Override
    public String encode() {
        return "D | " + super.encode() + " | " + this.by;
    }

    /**
     * Returns a user-friendly string representation of the object.
     *
     * @return A user-friendly string representation.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
