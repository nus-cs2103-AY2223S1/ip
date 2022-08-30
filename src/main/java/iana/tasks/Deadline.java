package iana.tasks;

import iana.utils.DateTime;

/**
 * Deadline task.
 */
public class Deadline extends Task {
    protected String endTime;

    /**
     * Constructor for Deadline class.
     * @param task string of task description.
     * @param endTime deadline time.
     * @param isCompleted true if task has been completed.
     */
    public Deadline(String task, String endTime, boolean isCompleted) {
        super(task, "deadline", isCompleted);
        this.endTime = DateTime.parseToString(endTime);
    }

    /**
     * Returns string representation of deadline to be saved in storage.
     */
    @Override
    public String toFileData() {
        return "D | " + super.toFileData() + "| " + this.endTime;
    }

    /**
     * Returns string representation of deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), endTime);
    }
}
