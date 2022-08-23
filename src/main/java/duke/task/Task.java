package duke.task;

/**
 * Representation of Task to be done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description String of task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon of the Task.
     * @return "X" if done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the Task as done.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Unmarks the Task.
     */
    public void unMark() {
        isDone = false;
    }

    /**
     * Gets the status icon of the Task in a format suitable to save.
     * @return "1" if done, else "0".
     */
    public String statusIcon() {
        return isDone ? "1" : "0";
    }

    /**
     * Converts Task object into String form to save.
     * @return {StatusIcon} | {description}
     */
    public String storedString() {
        return statusIcon() + " | " + description;
    }

    /**
     * Converts Task object into its String representation.
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
