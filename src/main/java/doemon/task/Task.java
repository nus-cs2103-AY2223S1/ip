package doemon.task;

/**
 * Represents a task with a description.
 */
public class Task {
    /** Description of the task. */
    private String description;

    /** Boolean indicating if the task is marked as done. */
    private boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
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
     * Checks if the specified keyword appears in the task description.
     *
     * @param keyword String to be checked.
     * @return Boolean indicating if the keyword was found in the task description.
     */
    public boolean matchesDescription(String keyword) {
        // Remove task and mark label
        String taskString = this.toString().substring(7);
        if (taskString.toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }
        return false;
    }

    /**
     * Returns a string used to save the task.
     *
     * @return A string used to save the task.
     */
    public String getSaveString() {
        String isMarked = isDone ? "1" : "0";
        return String.format("%s | %s", isMarked, this.description);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.isDone ? "X" : " ", this.description);
    }
}
