package wanya.task;

import wanya.parser.DateTimeParser;

import java.time.DateTimeException;
import java.time.LocalDateTime;

/**
 * Represents the deadline task.
 */
public class Deadline extends Task{
    private LocalDateTime dueDate;
    private final String TASK_TYPE = "D";

    /**
     * Creates a Deadline object when given task name and due date.
     *
     * @param taskName name of the deadline task.
     * @param dueDate due date of the deadline task.
     * @throws DateTimeException if invalid date format is given.
     */
    public Deadline(String taskName, String dueDate) throws DateTimeException {
        super(taskName);
        this.dueDate = DateTimeParser.getDateTime(dueDate);
    }

    /**
     * Creates a Deadline object when given task name, completeness and due date.
     *
     * @param taskName name of the deadline task.
     * @param hasCompleted whether the task has been completed.
     * @param dueDate due date of the deadline task.
     * @throws DateTimeException if invalid date format is given.
     */
    public Deadline(String taskName, boolean hasCompleted, String dueDate) throws DateTimeException {
        super(taskName, hasCompleted);
        this.dueDate = DateTimeParser.getDateTime(dueDate);
    }

    /**
     * Returns the String representation of the deadline task.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + TASK_TYPE +"]" + super.toString() + "(by: "
                + DateTimeParser.getDateTimeString(dueDate) + ")";
    }

    /**
     * Encodes a deadline object to a String representation for storage.
     *
     * @return String representation of the deadline task in storage.
     */
    @Override
    public String toStorageString() {
        return TASK_TYPE + "|" + super.toStorageString() + "|"
                + DateTimeParser.getDateTimeStorage(dueDate);
    }
}
