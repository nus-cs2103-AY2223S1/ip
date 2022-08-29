package duke;

/**
 * Task is an abstract class that represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description the String that describes the task
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public abstract String getDataFormat();

    /**
     * Returns a string to indicate whether the task is marked as done or undone
     * with an X and whitespace respectively.
     *
     * @return a string "X" or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as undone.
     */
    public void markAsUnDone() {
        this.isDone = false;
    }

    /**
     * Splits the description of the task into individual words.
     *
     * @return An array of strings
     */
    public String[] splitDescriptionToWords() {
        return description.split(" ");
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return a string that represents the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
