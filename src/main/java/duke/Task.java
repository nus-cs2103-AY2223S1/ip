package duke;

/**
 * Describes a task which can either be done or not done.
 *
 * @author Lai Han Wen
 */
public class Task {

    protected String description;

    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description Description of a Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns a String "X" if task is done or a whitespace if task is not done.
     *
     * @return a String "X" if task is done or a whitespace if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the String representation of a task.
     *
     * @return the String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
