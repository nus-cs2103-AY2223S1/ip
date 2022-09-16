package duke.models;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the status icon of the current Task.
     * Task has a status icon of "X" if marked as done, and " " otherwise.
     *
     * @return Status icon of the current Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the current Task as completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }
}
