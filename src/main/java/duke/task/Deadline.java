package duke.task;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import duke.parser.DateParser;

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
        this.date = DateParser.parseToDate(date);
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: " + DateParser.dateToString(this.date) + ")";
    }

    @Override
    public String toCommand() {
        return TaskType.D + " | " + super.toCommand() + " /by " + DateParser.dateToCommand(this.date);
    }
}
