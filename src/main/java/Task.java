public abstract class Task {
    private String description;
    private boolean isComplete = false;

    /**
     * Constructor for Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Get the status icon of a task.
     *
     * @return Status icon of Task.
     */
    public String getStatusIcon() {
        return (isComplete ? "X" : " "); // mark completed task with X
    }

    /**
     * Mark Task as complete.
     *
     * @throws MarkToggleException if Task is already complete.
     */
    public void markComplete() throws MarkToggleException {
        if (this.isComplete) {
            throw new MarkToggleException();
        }
        this.isComplete = true;
    }

    /**
     * Mark Task as incomplete.
     *
     * @throws MarkToggleException if Task is incomplete.
     */
    public void markIncomplete() throws MarkToggleException {
        if (!this.isComplete) {
            throw new MarkToggleException();
        }
        this.isComplete = false;
    }

    /**
     * Get the String representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
