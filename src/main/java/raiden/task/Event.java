package raiden.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import raiden.parser.DateParser;

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
        this.date = DateParser.parseStringToDate(date);
    }

    /**
     * Returns the String representation of this Event task.
     *
     * @return The String representation of this Event task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.E + "]" + super.toString() + " (at: " + DateParser.parseDateToString(this.date) + ")";
    }

    /**
     * Returns the String representation of the command to initialise this Event task.
     *
     * @return The String representation of the command for this Event task.
     */
    @Override
    public String toCommand() {
        return TaskType.E + " | " + super.toCommand() + " /at " + DateParser.parseDateToCommand(this.date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String changeDate(String newDate) {
        this.date = DateParser.parseStringToDate(newDate);
        return "Understood, the task has been changed to the following:\n" + this;
    }
}
