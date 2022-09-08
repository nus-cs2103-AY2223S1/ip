package duke.task;

/**
 * Task consists of a description and can be marked as done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     * @param done If the Event task is done or not done.
     */
    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    /**
     * Returns the status icon if the Task is done.
     *
     * @return A String 'X' for tasks done and ' ' for tasks not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Getter for the description of the Task.
     *
     * @return The description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Getter for isDone.
     *
     * @return If this task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }
}
