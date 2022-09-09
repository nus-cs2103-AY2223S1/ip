package raiden.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import raiden.parser.DateParser;

/**
 * Represents a Deadline task with date and time.
 */
public class Deadline extends Task {
    private LocalDateTime date;

    /**
     * Creates a Deadline task with the given description and date.
     *
     * @param description The description of the Deadline task.
     * @param date The date which this Deadline task has to be completed by.
     * @throws DateTimeException if error occurs while interpreting the date/time given.
     */
    public Deadline(String description, String date) throws DateTimeException {
        super(description);
        this.date = DateParser.parseStringToDate(date);
    }

    /**
     * Returns the String representation of this Deadline task.
     *
     * @return The String representation of this Deadline task.
     */
    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: " + DateParser.parseDateToString(this.date) + ")";
    }

    /**
     * Returns the String representation of the command to initialise this Deadline task.
     *
     * @return The String representation of the command for this Deadline task.
     */
    @Override
    public String toCommand() {
        return TaskType.D + " | " + super.toCommand() + " /by " + DateParser.parseDateToCommand(this.date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String changeDate(String newDate) {
        this.date = DateParser.parseStringToDate(newDate);
        return "OK, the task has been changed to the following:\n" + this;
    }
}
