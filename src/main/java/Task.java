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

    /** The total number of Task instances. */
    protected static int totalTasks = 0;

    /**
     * Constructor for a Task, initializes task name
     *
     * @param taskName The title of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    public String getTaskIcon() {
        return "No Task Icon";
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
