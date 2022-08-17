public class Task {
    /**
     * The description of the task.
     */
    private String description;
    /**
     * A boolean indicating if the task is marked as done.
     */
    private boolean isDone;

    /**
     * Constructor for a Task object.
     * @param description the string description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of this task.
     * @return a string representing this task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
