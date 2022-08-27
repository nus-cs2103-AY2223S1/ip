package duke.task;

/**
 * Encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the {@code description}.
     * Initially the new {@code Task} is assumed not done.
     * @param description The description of the new {@code Task}.
     */
    public Task(String description) {
            this.description = description;
            this.isDone = false;
    }

    /**
     * Returns the status of this {@code Task}.
     * @return The status of this {@code Task}.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of this {@code Task}.
     * @return The description of this {@code Task}.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the status of this {@code Task} with the {@code status}.
     */
    public void setIsDone(boolean status) {
        this.isDone = status;
    }

    /**
     * Returns the string representation of this {@code Task} for display.
     *
     * @return The string representation of this {@code Task} for display.
     */
    public String toString() {
        return "["+ this.getStatusIcon() +"] " + this.description;
    }

}