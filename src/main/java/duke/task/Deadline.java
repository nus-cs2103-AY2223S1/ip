package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadline Class that encapsulates details of a task with a deadline.
 *
 * @author Elgin Lee
 */
public class Deadline extends Task {
    /** The date of deadline. */
    private LocalDate date;

    /**
     * Constructor of Deadline.
     *
     * @param taskName The Task's Name.
     * @param date The date of deadline.
     * @throws DateTimeParseException If date cannot be casted to LocalDate.
     */
    public Deadline(String taskName, String date) throws DateTimeParseException {
        super(taskName);
        this.date = LocalDate.parse(date);
    }

    /**
     * Constructor of Deadline.
     *
     * @param taskName The Task's name.
     * @param date The date of deadline.
     * @param isDone True if the task is done, false otherwise.
     */
    public Deadline(String taskName, String date, boolean isDone) throws DateTimeParseException {
        super(taskName, isDone);
        this.date = LocalDate.parse(date);
    }

    /**
     * Gets the date of Deadline.
     *
     * @return Date of deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * String representation of a Deadline.
     *
     * @return Deadline representation.
     */
    @Override
    public String toString() {
        String formattedDate = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

        // Basic task representation, mark status and task description (e.g. [X] sleep).
        String basicDescription = super.toString();

        return "[D]" + basicDescription + " (by: " + formattedDate + ")";
    }
}
