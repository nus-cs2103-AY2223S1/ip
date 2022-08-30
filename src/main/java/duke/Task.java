package duke;

/**
 * Represents a Task, which can have many types.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a task.
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the status of the task.
     * @return String the status of the task
     */
    public String getStatus() {
        return (isDone ? "x" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Undo Marking of the task being done.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    /**
     * Gets the description of the task.
     * @return String description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gets the string representation of status in binary.
     * @return String binary representing status.
     */
    public String binarytoString() {
        return isDone
                ? "1"
                : "0";
    }

    /**
     * Gets the storage string representation of the task.
     * @return The storage string representation of the task
     */
    public String storeToString() {
        return "Task type|binarytoString|task description";
    }

    /**
     * Gets the string representation of the task.
     * @return The string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatus() + "]" + " " + this.description;
    }

}
