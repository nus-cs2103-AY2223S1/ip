package duke.task;

public class Task {
    public String taskName;

    public boolean isDone;

    /**
     * Constructor for the Task class.
     *
     * @param taskName the name of the task
     */
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
     *
     * @return String representation of a tick or empty string
     */
    public String getStatusIcon() {
        return this.isDone ? "✔" : " ";
    }

    /**
     * Empty formatTask function to be overridden by subclasses.
     *
     * @return null
     */
    public String formatTask() {
        return null;
    }

    /**
     * Returns String of the task name (eg. [ ] read book).
     *
     * @return String representation of the task name
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }
}
