package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An event task with a description and an occurrence date.
 */
public class Event extends Task {

    private LocalDate at;

    /**
     * Creates a new event task with a description and an occurrence date.
     * @param description the description of the task
     * @param at the occurrence date of the task
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date of a event must be in the format of yyyy-mm-dd.");
        }
    }

    @Override
    public String fileFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, at);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + at.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
