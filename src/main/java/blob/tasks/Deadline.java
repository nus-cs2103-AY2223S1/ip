package blob.tasks;

import java.time.format.DateTimeParseException;

import blob.exception.InvalidDateFormatException;
import blob.parser.DateTimeParser;

/**
 * The Deadline class encapsulates a type of task to be done.
 * A Deadline task is a task that needs to be done before a specific time/date.
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a Deadline task with the given description and by date.
     *
     * @param description The given task description.
     * @param by The string representation of the given deadline of the task.
     * @throws InvalidDateFormatException
     */
    public Deadline(String description, String by) throws InvalidDateFormatException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = new DateTimeParser().parse(by);
        } catch (DateTimeParseException exception) {
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s (by: %s)", super.toString(), by);
    }

    /**
     * Returns the formatted Event task details to be stored in text file.
     *
     * @return Formatted Event task details to be stored in text file
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + this.by;
    }
}
