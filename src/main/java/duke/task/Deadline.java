package duke.task;

import duke.common.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline-type task.
 */
public class Deadline extends Task {
    private LocalDate localDate;

    /**
     * Constructs a deadline task.
     *
     * @param description the description of the task
     * @param isDone denotes whether the task is marked done or not
     * @param localDate the date of the deadline
     * @throws DukeException if the description of the task is empty
     */
    public Deadline(String description, boolean isDone, LocalDate localDate) throws DukeException {
        super("deadline", description, isDone);
        this.localDate = localDate;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return super.toString() + " (by: " + this.localDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + ")";
    }

    /**
     * Returns a string encoding the deadline task for saving the task in the save file.
     *
     * @return the string encoding
     */
    @Override
    public String encode() {
        return super.encode() + Task.ENCODING_SEPARATOR + this.localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
