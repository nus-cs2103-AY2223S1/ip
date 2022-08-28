package duke;

/**
 * Represents tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns status icon of task.
     *
     * @return Status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns status icon of task in saved file
     *
     * @return String of formatted data.
     */
    public String getStatusIconSave() {
        return isDone ? "Done" : "NotDone";
    }

    /**
     * Formats data to be saved.
     *
     * @return String of formatted data.
     */
    public String savedData() {
        return getStatusIconSave() + " | " + description + " | ";
    }

    /**
     * Formats string of task to be added to list.
     *
     * @return String of formatted task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
