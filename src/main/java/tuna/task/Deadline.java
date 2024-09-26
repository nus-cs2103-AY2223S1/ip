package tuna.task;

import tuna.TunaException;

/**
 * Represents a Deadline task.
 */
public class Deadline extends TimeBasedTask {
    /**
     * Creates a task with a deadline.
     *
     * @param description the description of the task.
     * @param by the deadline of the task.
     * @throws TunaException Exception thrown when the date and time provided is not formatted correctly.
     */
    public Deadline(String description, String by) throws TunaException {
        super(description, "D", by);
    }

    /**
     * Returns string representation of the Deadline object.
     *
     * @return String representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[" + super.getTaskType() + "]" + super.toString() + " (by: " + parseDateTime(this.getDateTime()) + ")";
    }
}
