package duke.task;

/**
 * Represents a task unit.
 * A task unit can be an event, deadline or to-do
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task that is not done yet
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns whether a task is completed
     *
     * @return whether task is done
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Marks the task as completed
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the task which suggests task has yet to be completed
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns the description of the task
     *
     * @return task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a string representation of this task
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns a simple string representation of this task
     *
     * @return simple string representation
     */
    public String toSimpleString() {
        return (getStatusIcon().equals("X") ? "1" : "0") + " | " + this.description;
    }

}
