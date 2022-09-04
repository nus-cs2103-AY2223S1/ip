package duke.types;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Represents an Event as a task.
 *
 * @author Aaron Tan
 */
public class Event extends Task {
    private LocalDateTime dateTime;
    private DateTimeFormatter formatter;

    /**
     * Constructor for Event class.
     *
     * @param description Description of task.
     * @param isDone If task is done or not done.
     * @param dateTimeString Date and time represented as a string.
     * @throws DukeException If date or time is given in an invalid format.
     */
    public Event(String description, boolean isDone, String dateTimeString) throws DukeException {
        super(description, isDone);
        try {
            formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            dateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("wrong format!");
        }
    }

    /**
     * Generates a String to be saved.
     *
     * @return String in the format of E | isDone | description | datetime.
     */
    public String saveString() {
        return String.format("E | %s | %s | %s\n", super.isDone ? "1" : "0",
                super.getDescription(), dateTime.format(formatter));
    }

    /**
     * Generates a String for representation during list.
     *
     * @return String in the format of [E][isDone] description (at: datetime)
     */
    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)",
                super.toString(), dateTime.format(formatter));
    }
}
