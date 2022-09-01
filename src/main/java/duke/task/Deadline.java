package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;

/**
 * Represents a task of type Deadline.
 */
public class Deadline extends Task {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate date;

    /**
     * Constructor for Deadline task.
     */
    public Deadline(String description, LocalDate date) throws DukeException {
        super(description);
        this.date = date;
        if (description.isBlank()) {
            throw new DukeException("Take me seriouslyy :( What do you want to do?\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(formatter) + ")";
    }
}
