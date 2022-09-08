package duke;

/**
 * Represents a task that can be added to TaskList. A Task object contains a String description and a boolean
 * representing the completion status of the task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    private String tag;

    /**
     * Creates new Task object.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return this.tag;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Checks if description of task contains a certain string.
     *
     * @param keyword Keyword searched by user.
     * @return Boolean representing whether description of task contains keyword.
     */
    public boolean containsKeyword(String keyword) {
        keyword = keyword.toLowerCase();
        return this.description.contains(keyword);
    }

    public abstract String getStorageString();

    /**
     * Returns String representation of Task object.
     *
     * @return String representation.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
