package duke.task;

import duke.exception.DukeException;
import duke.exception.DukeInvalidDateException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task with a due date.
 *
 * @author Lim Ai Lin
 */
public class Deadline extends Task {
    private final LocalDate DATE;
    private final String BY;

    /**
     * Creates a new unmarked Deadline task object with a due date.
     *
     * @param description The name of the Deadline task.
     * @param by The date by which the task should be completed by.
     * @throws DukeException
     *          Thrown when the wrong date format is entered.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        this.BY = by;
        try {
            this.DATE = LocalDate.parse(by, formatter);
        } catch (Exception e) {
            throw new DukeInvalidDateException();
        }
    }

    /**
     * Gets the due date of the Deadline task.
     *
     * @return The deadline of the task.
     */
    public String getBy() {
        return BY;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + DATE.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
