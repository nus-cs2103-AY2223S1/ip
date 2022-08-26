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
     * Checks to see if the task description contains the given keyword.
     * @param keyword The word that we want to check whether is in the task description.
     * @return True if the keyword is in the description, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns what the task is about, and also an icon to indicate if the task is done.
     * @return What the task is about, and also an icon to indicate if the task is done.
     */
    public String toString() {
        return (this.getStatusIcon() + " | " + this.description);
    }

    public static void main(String[] args) {
        Task t = new Task("return books");
        t.hasKeyword("return");
    }
}
