package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns "X" if task is done
     * @return "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Gets string to be saved in the storage file
     * @return file string
     */
    public abstract String toFileString();

    /**
     * Marks task as complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Marks task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }
}
