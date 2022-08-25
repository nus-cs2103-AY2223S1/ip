package duke;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks a task as completed
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not completed
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon indicating whether the task has been marked as completed
     * @return the status icon
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Generates an encoding of the Task for use in storage
     * @return encoded string following the storage format
     */
    public String getStorageString() {
        return " " + "|" + (this.isDone ? "1" : "0") + "|" + this.getDescription();
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
