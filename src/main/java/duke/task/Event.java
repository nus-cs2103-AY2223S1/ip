package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.parser.DateParser;

/**
 * Represents an Event task with date and time.
 */
public class Event extends Task {
    private LocalDateTime date;

    /**
     * Creates an Event task with the given description and date.
     *
     * @param description The description of the Event task.
     * @param date The date which this Event task has to be completed by.
     * @throws DateTimeException if error occurs while interpreting the date/time given.
     */
    public Event(String description, String date) throws DateTimeException {
        super(description);
        this.date = DateParser.parseToDate(date);
    }

    /**
     * Returns the String representation of this Event task.
     *
     * @return The String representation of this Event task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: " + DateParser.dateToString(this.date) + ")";
    }

    /**
     * Returns the String representation of the command to initialise this Event task.
     *
     * @return The String representation of the command for this Event task.
     */
    @Override
    public String toCommand() {
        return TaskType.E + " | " + super.toCommand() + " /at " + DateParser.dateToCommand(this.date);
    }
}
