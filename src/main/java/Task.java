/**
 * The Task class represents a task added to Duke.
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Constructs a new Task with a specified description.
     *
     * @param description A string specifying the description of the Task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks a Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks a Task as not done.
     */
    public void unmarkAsDone() {
        isDone = false;
    }

    /**
     * Returns an icon that represents if the task is done or not.
     *
     * @return "X" if the task is done, " " if not.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the string representation of a Task.
     *
     * @return The string representing the Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
