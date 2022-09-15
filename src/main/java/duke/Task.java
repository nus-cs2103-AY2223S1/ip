package duke;

/**
 * Represents task object.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        assert description.length() > 0 : "description should not be empty";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets status icon of task.
     *
     * @return String of status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns formatted string of data to be saved.
     *
     * @return Formatted string of data.
     */
    public String printSavedData() {
        return getStatusIcon() + " | " + description + " | ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
