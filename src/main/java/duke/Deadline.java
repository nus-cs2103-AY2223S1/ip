package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * This class encapsulates a Deadline task.
 *
 * @author Andrew Lo Zhi Sheng
 * @version CS2103T AY22/23 Semester 1
 */
public class Deadline extends Task {
    // Fields
    protected LocalDate by;

    /**
     * Constructor for a deadline instance.
     *
     * @param description the description of the task
     * @param by the due date of the task
     *
     * @throws DukeException if the given date is invalid
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (java.time.format.DateTimeParseException e) {
            throw new DukeException("Invalid Date Format, please input it as YYYY-MM-DD");
        }
    }

    /**
     * String representation of the deadline.
     *
     * @return String representing this task
     */
    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ")";
    }

    /**
     * Gets the deadline of the duke.Task, if any.
     *
     * @return A LocalDate representing the duke.Task's deadline if it exists
     *         null if no such deadline exists
     */
    @Override
    public LocalDate getDeadline() {
        return this.by;
    }

    /**
     * Gets the string representation of this task for storage in a file.
     *
     * @return a String containing the task name, description, whether it is
     *         completed, and the due date.
     */
    public String encode() {
        return String.format("%s # %s # %s",
                "D",
                super.encode(),
                this.by);
    }
}
