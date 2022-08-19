/**
 * Task is a representation of a task in Duke.
 */
public abstract class Task {
    /** Description of the task. */
    private final String description;
    /** Whether the task is done. */
    private boolean isDone;

    /**
     * Constructor for a Task, with a description.
     * Task is set as "not done" when created.
     *
     * @param description Description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Gets the task in a format for file saving.
     *
     * @return The task in file saving format.
     */
    public String getFileFormat() {
        return String.format("%s | %s", this.isDone ? "1" : "0", this.description);
    }

    /**
     * Gets the string representation of a task.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        String doneIcon = this.isDone ? "X" : " " ;
        return String.format("[%s] %s", doneIcon, this.description);
    }
}
