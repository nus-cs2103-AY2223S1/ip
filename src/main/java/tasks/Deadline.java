package tasks;

import exception.InvalidDateFormatException;
import parser.DateTimeParser;

import java.time.format.DateTimeParseException;

/**
 * The Deadline class encapsulates a type of task to be done.
 * A Deadline task is a task that needs to be done before a specific time/date.
 */
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws InvalidDateFormatException {
        super(description, TaskType.DEADLINE);
        try {
            this.by = new DateTimeParser().parse(by);
        } catch (DateTimeParseException exception){
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
        return super.toFileString() + " | " + this.by ;
    }
}
