package duke.task;

/**
 * Represents a description of what a user has planned to do.
 */
public class Task {
    protected String description;
    protected boolean isCompleted;
    protected String tag;
    protected static final String NO_TAG = " ";

    /**
     * Initialises a <code>Task</code> object that represents the task that
     * the user has added. The task is marked incomplete by default.
     * @param name Description of the <code>Task</code>.
     * @param tag Tag of the <code>Task</code>.
     */
    public Task(String name, String tag) {
        this.description = name;
        this.isCompleted = false;
        this.tag = tag;
    }

    /**
     * Returns the status of the task.
     * @return The status of the task.
     */
    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        isCompleted = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void markAsNotCompleted() {
        isCompleted = false;
    }

    /**
     * Returns the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the tag of the task.
     * @return Tag of the task.
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Returns the status, tag and description of the task.
     * @return The status, tag and description of the task.
     */
    @Override
    public String toString() {
        return this.tag.equals(NO_TAG)
                ? "[" + this.getStatus() + "] " + this.description
                : "[" + this.getStatus() + "] " + this.description + " #" + this.tag;
    }
}
