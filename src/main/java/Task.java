/**
 * Class Task that encapsulates details of a task.
 *
 * @author Elgin Lee
 */
public class Task {
    /** Name of the task. */
    protected String taskName;

    /** Whether the task has been completed. */
    protected boolean isDone;

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

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.taskName;
    }
}
