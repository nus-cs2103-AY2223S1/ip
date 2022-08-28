package duke;

/**
 * Abstract class which encapsulates a Task inputted by the user.
 *
 */
abstract public class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructor.
     *
     * @param name
     */
    public Task(String name) {
        this.name = name;
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
     * Returns String representation of the Task.
     *
     * @return String representation of the Task
     */
    public String toString() {
        return "[" + this.getStatusIcon() +"] " + this.name;
    }

    /**
     * Returns "X" if task is done, " " otherwise.
     *
     * @return status icon
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Returns true if task is marked as done, false otherwise.
     *
     * @return isDone boolean of the Task
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns name of the task.
     *
     * @return name of the task
     */
    public String getName() {
        return this.name;
    }

}
