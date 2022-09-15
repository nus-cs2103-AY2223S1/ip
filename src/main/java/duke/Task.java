package duke;

/**
 * Represents the most basic <code>Task</code>.
 */
public class Task {
    protected String description;
    private boolean isDone;

    /**
     * Construct a <code>Task</code>.
     *
     * @param description description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Construct a <code>Task</code>.
     *
     * @param description description of task.
     * @param isDone state of task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Changes state of task to done.
     */
    public void setDone() {
        if (this.isDone) {
            return;
        }
        this.isDone = true;
    }

    /**
     * Changes state of task to be undone.
     */
    public void setNotDone() {
        if (!this.isDone) {
            return;
        }
        this.isDone = false;
    }

    public boolean startsWith(String prefix) {
        return description.startsWith(prefix);
    }

    /**
     * Returns string representation of the task.
     *
     * @return string representation of the task.
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + description;
        } else {
            return "[ ] " + description;
        }
    }

    /**
     * Returns formatted string to represent task in storage.
     *
     * @return formatted string.
     */
    public String toStorageFormat() {
        if (this.isDone) {
            return "1 | " + description;
        } else {
            return "0 | " + description;
        }
    }


}
