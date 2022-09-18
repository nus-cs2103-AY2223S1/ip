package duke.task;

/**
 * Class Task that encapsulates details of a task.
 *
 * @author Elgin Lee
 */
public class Task {
    /** Name of the task. */
    private String taskName;

    /** Whether the task has been completed. */
    private boolean isDone;

    /**
     * Constructor for a Task, initializes task name
     *
     * @param taskName The title of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Constructor for a Task, initializes both task name and isDone.
     *
     * @param taskName The title of the task.
     * @param isDone True if task is already done, false if not done.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    /**
     * Checks whether the task is done or not.
     *
     * @return True if task is done, False otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Gets the task.
     *
     * @return The task's name.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns status of the task (difference depends on whether it is done or not done).
     *
     * @return [X] if task is done, [ ] otherwise.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks the task as done, and sets isDone to true.
     *
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task, and sets isDone to false.
     *
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * String representation of a Task.
     *
     * @return Status concatenated with the task's name.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + this.taskName;
    }
}
