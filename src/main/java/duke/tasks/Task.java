package duke.tasks;

/**
 * Represents a task. A <code>Task</code> object has two attributes: task name and if the task is done.
 */

public class Task {
    protected String name;
    protected boolean isDone;

    /**
     * Initializes Task object and set done status to false.
     * @param taskName name of task
     */
    public Task(String taskName) {
        assert taskName != null;
        name = taskName;
        isDone = false;
    }

    /**
     * Returns String based on done status of task.
     * @return X is task is done, else returns a space.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns string representation of Task, including task name and done status.
     * @return String representation of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }

    /**
     * Set done status of task to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set done status of task to false.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

}
