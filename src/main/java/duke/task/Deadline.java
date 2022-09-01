package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date.
 * @author Lim Ai Lin
 */
public class Deadline extends Task {
    private final LocalDate date;
    private final String by;

    /**
     * Creates a new unmarked Deadline task object with a due date.
     * @param description The name of the Deadline task.
     * @param by The date by which the task should be completed by.
     * @throws DukeException
     *          Thrown when the wrong date format is entered.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        this.by = by;
        try {
            this.date = LocalDate.parse(by, formatter);
        } catch (Exception e) {
            throw new DukeException("Please enter date in the format: dd/M/yyyy");
        }
    }

    /**
     * Gets the due date of the Deadline task.
     * @return The deadline of the task.
     */
    public String getBy() { return by; }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
