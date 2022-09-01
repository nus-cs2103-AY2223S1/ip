package seedu.duke;

/**
 * Represents a description and if the task is completed.
 */
public class Task {
    /* The name of the task */
    protected String description;
    /* Represents whether the task is completed */
    protected boolean isDone;

    /**
     * Constructor for Task
     *
     * @param description name of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is completed.
     *
     * @return X if completed and empty string if not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the task to be completed.
     */
    public String setDone() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n[X] " + this.description;
    }

    /**
     * Set the task to be uncompleted.
     */
    public String setUndone() {
        this.isDone = false;
        return "OK, I've marked this task as not done yet:\n[ ] " + this.description;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
