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
    protected int priorityLevel;

    /**
     * public constructor for Task.
     * @param task
     */
    public Task(String task) {
        this.task = task;
        this.done = "[ ]";
        this.priorityLevel = 0; //default value for priority level.
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
     * public priorityLevel method to allow users set specific priority for tasks. Default value is 0.
     * @param priorityLevel
     */
    public void priority(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    /**
     * class method to return String representation of Task.
     * @return String.
     */
    @Override
    public String toString() {
        return String.format("%s %s (Priority %d)", this.done, this.task, this.priorityLevel);
    }
}
