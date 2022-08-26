package duke;

/**
 * Represents a task that has a description and an indicator of whether it is done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class.
     * @param description Provides information about what the task is.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns one of two possible icons, depending on whether the task is done.
     * @return "X" if task is done, and "O" if task is not done.
     */
    public String getStatusIcon() {
        return (this.isDone ? "X" : "O");
    }

    /**
     * Returns the description of the task.
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Indicates that a task has been done.
     */
    public void markAsDone() {
        this.isDone = true;
        System.out.println("     Task has been marked as done!: \n"
                          + "       "
                          + this.toString());
    }

    /**
     * Indicates that a task is undone.
     */
    public void markAsUnDone() {
        this.isDone = false;
        System.out.println("     Task has been marked as NOT done!: \n"
                + "       "
                + this.toString());
    }

    /**
     * Returns what the task is about, and also an icon to indicate if the task is done.
     * @return What the task is about, and also an icon to indicate if the task is done.
     */
    public String toString() {
        return (this.getStatusIcon() + " | " + this.description);
    }
}
