package duke.task;

import duke.DukeCommand;

/**
 * A Task to complete.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     * @param description The description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the completion status of the Task.
     * @return A boolean representing the completion status.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Gets the String representation completion status of the Task.
     * @return A String representing the completion status.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Gets the DukeCommand associated with this Task type.
     * @return The DukeCommand associated with this Task type.
     */
    public DukeCommand getTaskType() {
        return DukeCommand.UNKNOWN;
    }

    /**
     * Gets the description of the Task.
     * @return The description of the Task.
     */
    public String getDesc() {
        return this.description;
    }

    /**
     * Gets any additional data of the Task.
     * @return A String representing any additional data.
     */
    public String getOtherData() {
        return "";
    }

    /**
     * Marks this Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this Task as not done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
