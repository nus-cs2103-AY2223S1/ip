package duke;
/**
 * The task class.
 */
  public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task Object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the right icon.
     *
     * @return The string icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Unmarks the task.
     */
    public void unMark() {
        this.isDone = false;
    }

    /**
     * Get the description of the task.
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "["+ this.getStatusIcon() + "] "
                + this.getDescription();
    }

}
