package duke;

/**
 * Encapsulates a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Returns a string representation of the status of the task.
     *
     * @return the string "[X]" if the task is done and "[ ]" otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDone(boolean bool) {
        this.isDone = bool;
    }

    /**
     * Returns a string that represents this task to be saved into a data file.
     *
     * @return the string to be saved
     */
    public String toSaveData() {
        return this.description;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the status icon concatenated with the task description.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

}
