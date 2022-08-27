package skylark.task;

/** Represents a Task that is created by the user. */
public abstract class Task {
    /** Description of the task inputted by the user. */
    private final String description;

    /** Represents whether the task is marked as done. */
    private boolean isDone;

    /**
     * Returns a Task object.
     * Initialises the description variable and marks the Task as undone.
     *
     * @param description Description of the task inputted by the user.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns a string representation on whether the task is done.
     *
     * @return "X" if the task is done, " " if the task is undone.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description of the Task.
     *
     * @return Description inputted by the user.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of a particular task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a string representation of the task.
     * This string is used to save the task into the file for storage.
     *
     * @return String representation of the task that is parsable by the Storage object.
     */
    public String toStringFile() {
        return String.format("%d | %s", this.getStatusIcon().equals("X") ? 1 : 0, this.getDescription());
    }
}
