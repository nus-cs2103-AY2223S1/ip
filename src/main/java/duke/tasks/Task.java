package duke.tasks;

/**
 * Represents a task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor for a Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the Task.
     * @return "X" if the Task is done or a blank String is the Task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Changes the status of the Task.
     * @param isDone True if the Task is to be marked as done and false if the Task is to be marked
     *        as not done.
     */
    public void changeStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the description of the Task.
     * @return The description of the Task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the description of the Task contains the keyword.
     * @param keyword The keyword to check the Task's description for.
     * @return True if the description of the Task contains the keyword.
     *         If the keyword is an empty string, return False.
     */
    public boolean hasKeyword(String keyword) {
        if (keyword.isEmpty()) {
            return false;
        }

        return description.contains(keyword);
    }

    /**
     * Returns the String representation of the Task.
     * @return a String indicating the Task's status and description.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Returns the type of the Task.
     * @return The symbol representing the Task's type.
     */
    public abstract String getType();

    /**
     * Returns the date of the Task.
     * @return the date of the Task if the Task has a date.
     */
    public abstract String getDate();

    /**
     * Checks if the Task occurs on a specific date.
     * @param date The date to check if the Task occurs on.
     * @return True if the Task occurs on the given date.
     */
    public abstract boolean isOnDate(String date);
}
