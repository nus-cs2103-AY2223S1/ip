package duke.task;

public class Task {
    // Class Fields
    protected String taskName;
    public boolean isDone;

    // Constructor
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string of tick or empty string depending on whether the task is done or not.
     * @return String representation of a tick or empty string
     */
    public String getStatusIcon() {
        return this.isDone ? "✔" : " ";
    }

    public String inputToTxt() {
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }
}