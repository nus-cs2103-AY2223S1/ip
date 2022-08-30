package duke;

/**
 * Represents task object
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of task
     *
     * @return String of status of task
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns formatted string of data to be saved
     *
     * @return Formatted string of data
     */
    public String savedData() {
        return getStatusIcon() + " | " + description + " | ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
