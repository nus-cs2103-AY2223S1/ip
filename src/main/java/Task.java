/**
 * Parent Class for all Tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a Task.
     *
     * @param description the description of the Task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the Task as completed.
     *
     * @return string containing the completed message, to be displayed to the user
     */
    public String mark() {
        this.isDone = true;
        return " Great job! I will mark the task as completed.\n"
            + "   " + this;
    }

    /**
     * Sets the Task as uncompleted.
     *
     * @return string containing the uncompleted message, to be displayed to the user
     */
    public String unmark() {
        this.isDone = false;
        return " Understood. I will mark the task as uncompleted.\n"
            + "   " + this;
    }

    @Override
    public String toString() {
        String symbol = this.isDone ? "X" : " ";
        return "[" + symbol + "] " + this.description + "\n";
    }
}
