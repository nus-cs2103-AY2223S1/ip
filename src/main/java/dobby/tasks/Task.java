package dobby.tasks;

/**
 * Task is a class that represents a task with its description as well is status.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of the Task class.
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor of the Task class.
     *
     * @param desc The description of the task
     * @param isDone The status of the task
     */
    public Task(String desc, boolean isDone) {
        this.description = desc;
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the completion status of the task.
     *
     * @return X if task is completed, " " otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not complete.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return true if completed, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the formatted task to be saved in the external file.
     *
     * @return String of formatted task
     */
    public String toPrint() {
        return " | " + "[" + getStatusIcon() + "] | " + description;
    }

    /**
     * Checks if task description contains specified string.
     *
     * @param toFind The specified string
     * @return True of specified string is present, false otherwise
     */
    public boolean isPresent(String toFind) {
        boolean isPresent = description.contains(toFind);
        return isPresent;
    }

    /**
     * Returns the formatted task to be printed out.
     *
     * @return String of formatted task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
