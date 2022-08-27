package duke;

/**
 * An abstract class to represent a generic task containing a description and whether it has been done
 * or not.
 */
abstract public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task with the default value of false for isDone.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of a task.
     * This method should be overridden by all child classes.
     *
     * @return the string representation of a task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    /**
     * Returns the encoded representation of a task to be stored in a file.
     * All child classes should implement this function.
     *
     * @return The encoded representation of a task.
     */
    abstract public String encode();
}
