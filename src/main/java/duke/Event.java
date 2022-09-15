package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * An event task with a description and an occurrence date.
 */
public class Event extends Task {

    private LocalDate startDate;

    /**
     * Creates a new event task with a description and an occurrence date.
     * @param description the description of the task
     * @param startDate the occurrence date of the task
     */
    public Event(String description, String startDate) throws DukeException {
        super(description);
        try {
            this.startDate = LocalDate.parse(startDate);
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The date of a event must be in the format of yyyy-mm-dd.");
        }
    }

    @Override
    public String fileFormat() {
        return String.format("E | %d | %s | %s", isDone ? 1 : 0, description, startDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + startDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
