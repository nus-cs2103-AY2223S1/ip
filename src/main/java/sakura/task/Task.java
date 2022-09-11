package sakura.task;

/**
 * Abstract representation of a task in the Sakura database.
 */
public abstract class Task implements java.io.Serializable, Comparable<Task> {
    protected String description;
    protected boolean isDone;
    protected String date;

    /**
     * Default constructor for Task.
     *
     * @param description description of the task.
     * @param date date of the task.
     */
    public Task(String description, String date) {
        this.description = description;
        this.isDone = false;
        this.date = date;
    }

    /**
     * Returns the string description of a task.
     *
     * @return string description of a task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if a task is completed.
     *
     * @return X if a task is completed, empty if not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks a task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    public abstract String stringifyTask();

    /**
     * Compares this task with the another task t for order based on date. The task with no date is considered greatest,
     * followed by the task with a later date.
     *
     * @param t the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Task t) {
        if (this.date == null) {
            return 1;
        } else if (t.date == null) {
            return -1;
        } else {
            return this.date.compareTo(t.date);
        }
    }

    /**
     * Returns a string representation of a task description and its completion status.
     *
     * @return String representation of a task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
