package tasks;

import exception.InvalidDateFormatException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Event class encapsulates a type of task to be done.
 * A Deadline task is a task that starts at a specific time and ends at a specific time.
 */
public class Event extends Task {
    private LocalDate at;

    public Event(String description, String at) throws InvalidDateFormatException {
        super(description, TaskType.EVENT);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException exception){
            throw new InvalidDateFormatException();
        }
    }

    /**
     * Returns a string representation of the Event task.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("%s (at: %s)", super.toString(),
                this.at.format(DateTimeFormatter.ofPattern("d MMM yyyy")));
    }

    /**
     * Returns the formatted Event task details to be stored in text file.
     *
     * @return Formatted Event task details to be stored in text file
     */
    @Override
    public String toFileString() {
        return super.toFileString() + " | " + this.at ;
    }
}
