package duke.task;

/**
 * Abstract class representing a Task.
 */
public abstract class Task {

    private String description;
    private boolean isDone;

    /**
     * Constructor for Task for subclasses.
     * @param description The task at hand.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for Task for subclasses.
     * @param description The task at hand.
     * @param isDone Whether task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completion symbol for task.
     * @return An icon representing done or not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unMarkDone() {
        this.isDone = false;
    }

    /**
     * Returns the for description.
     * @return The description of Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns isDone.
     * @return The completion status of Task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns the String representation of Task.
     * @return The String representation of Task showing the status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", this.getStatusIcon(), this.description);
    }
}
