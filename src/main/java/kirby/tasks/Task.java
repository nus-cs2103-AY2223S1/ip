package kirby.tasks;

/**
 * Task class is an abstract class that is inherited by the specific tasks.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the class Task.
     *
     * @param description description of a task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Stores the task date in the form of [dd, mm, yyyy].
     *
     * @return array containing the task date.
     */
    public abstract int[] getDate();

    /**
     * Marks a task completed.
     */
    public void setCompleted() {
        this.isDone = true;
    }

    /**
     * Marks a task incomplete.
     */
    public void setIncomplete() {
        this.isDone = false;
    }

    /**
     * Displays the task status according to whether it is marked completed.
     *
     * @return a string containing a filled or unfilled box depending on if it is complete.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the format when the task is written in a text file.
     *
     * @return a string containing the written version of the task description.
     */
    public String toFileOutput() {
        return null;
    }
}