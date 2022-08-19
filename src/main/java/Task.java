public class Task {
    private final String description;
    private boolean isDone;

    /**
     * Initialises a Task with a description.
     *
     * @param description A string representing the Task's description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the char representation of whether a Task is done.
     *
     * @return The char representing the Task's status.
     */
    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Set the Task's isDone to isDone.
     * @param isDone The boolean to set the Task's isDone to.
     */
    protected void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return The string representing the Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
