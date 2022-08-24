/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

/**
 * Class Task to store Task.
 */
public abstract class Task {
    protected String task;
    protected String done;

    /**
     * public constructor for Task.
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.done = "[ ]";
    }

    /**
     * public method mark to mark Task.
     */
    public void mark() {
        this.done = "[X]";
    }

    /**
     * public method mark to unmark Task.
     */
    public void unmark() {
        this.done = "[ ]";
    }

    /**
     * public getter method that returns task name.
     * @return name of the task.
     */
    public String getTaskName() {
        return this.task;
    }

    /**
     * class method to return String representation of Task.
     * @return String.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.done, this.task);
    }
}
