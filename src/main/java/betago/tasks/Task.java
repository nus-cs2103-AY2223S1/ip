package betago.tasks;

/**
 * Task that stores a string description and boolean isDone.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the corresponding status icon of the task.
     * "X" represents that the task is marked done.
     * " " represents that the task is not marked as done.
     *
     * @return String icon representing status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the description of the task.
     *
     * @return String description of the task.
     */
    public String getTaskDescription() {
        return this.description;
    }

    /**
     * Marks the task as done by setting boolean isDone to true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done by setting boolean isDone to false.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String saveTask() {
        return "";
    }
}
