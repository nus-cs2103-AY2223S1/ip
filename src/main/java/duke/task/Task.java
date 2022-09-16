package duke.task;

/**
 * The Task class which represents a task.
 *
 * @author Leong Jia Hao Daniel
 */
public class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructs a task specified by the user.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        assert !description.isEmpty();
        this.isDone = false;
    }

    public Task() {}

    /**
     * Returns the completion status of the task. If the
     * task is completed return "X" else " ".
     *
     * @return "X" or " " based on the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void markAsIncomplete() {
        this.isDone = false;
    }

    /**
     * Returns a String which is stored in the file.
     *
     * @return The task but formatted in the way it is meant to
     *         be stored in the file.
     */
    public String toDataFormat() {
        return "";
    }

    /**
     * Overrides the String implementation Task.
     *
     * @return A string that shows the completion status of task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
