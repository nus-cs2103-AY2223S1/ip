package duke;

/**
 * Parent class of Todo, Deadline and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * @return String "X" or " " depending on whether the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * @return String for user reading
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), this.description);
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * @return String for storage in file.
     */
    public abstract String toFileString();
}
