package duke.task;

/**
 * Parent class to manage all the Task
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String symbol;

    /**
     * Constructor for the Task class
     *
     * @param description description of the task
     * @param symbol symbol of the task
     */
    public Task(String description, String symbol) {
        this.description = description;
        this.isDone = false;
        this.symbol = symbol;
    }

    /**
     * Method to get the status icon of the task
     *
     * @return the status icon of the task
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    /**
     * Method to mark the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Method to unmark the task as done
     */
    public void markAsUndone() {
        isDone = false;
    }

    /**
     * Method to get the description of the task
     *
     * @return the description of the task
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Method to get the symbol of the task
     *
     * @return the symbol of the task
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Method to get the info of the task
     * in the format of [T]--[ ]--{task name}
     *
     * @return the info of the task
     */
    public String getInfo() {
        return (getSymbol() + "--"
                + getStatusIcon() + "--"
                        + getDescription());
    }

    /**
     * Method to return a string representation of thetask
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return getStatusIcon() + " "
                + getDescription();
    }
}
