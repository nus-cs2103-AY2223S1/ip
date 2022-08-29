package duke.task;

/**
 * Task is a task that has a specific task type.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description String description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status icon if the task is marked or not.
     *
     * @return Marked status of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks tasks as done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Returns saved task in String representation.
     *
     * @return String representation of saved task.
     */
    public String saveTask() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]" + " " + this.description;
    }
}
