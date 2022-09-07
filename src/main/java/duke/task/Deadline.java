package duke.task;

import java.time.LocalDate;

import duke.Parser;
import duke.exception.DukeException;

/**
 * Class containing information regarding
 * task of type Deadline.
 *
 * @author Elbert Benedict
 */
public class Deadline extends Task {
    public static final String TYPE_SYMBOL = "[D]";
    private LocalDate deadline;

    /**
     * Constructs a new Deadline instance.
     *
     * @param task the task description.
     * @param deadline the deadline of the task.
     * @throws DukeException If deadline is not a valid date.
     */
    public Deadline(String task, String deadline) throws DukeException {
        super(task);
        this.deadline = Parser.convertToDateObj(deadline);
    }

    /**
     * Constructs a new Deadline instance.
     *
     * @param task the task description.
     * @param deadline the deadline for the task.
     * @param isDone whether the task has been marked as done.
     * @throws DukeException If deadline is not a valid date.
     */
    public Deadline(String task, String deadline, boolean isDone, String priority) throws DukeException {
        super(task, isDone, priority);
        this.deadline = Parser.convertToDateObj(deadline);
    }

    /**
     * Returns a string representation of the
     * Deadline instance.
     *
     * @return string representation of the
     *     Deadline instance.
     */
    @Override
    public String toString() {
        return TYPE_SYMBOL + super.toString()
                + " (by: " + Parser.printDate(deadline) + ")";
    }

    /**
     * Returns the string representation for the Deadline instance
     * for the save file.
     *
     * @return the string representation for the Deadline instance
     *     for the save file.
     */
    @Override
    public String toSaveFileString() {
        return TYPE_SYMBOL + " @ " + getStatusIcon() + " @ " + super.getPriority()
                + " @ " + super.getTask() + " @ " + Parser.printSaveFileDate(deadline);
    }
}
