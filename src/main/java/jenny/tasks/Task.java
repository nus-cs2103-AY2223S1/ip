package jenny.tasks;

import jenny.exceptions.JennyException;

/**
 * Abstract class for all other different types of tasks.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public abstract class Task {
    protected static final String ERROR_INVALID_DESCRIPTION = "The description of this task cannot be empty.";
    protected static final String ERROR_INVALID_DUE_DATE = "The due date of this task cannot be empty.";
    private static final String MESSAGE_SCOPE = Task.class.getSimpleName();
    protected final String description;
    protected boolean isDone;


    /**
     * Constructor for an instance of a new task.
     * Will initialise a new task with the provided {@code description}.
     * By default, the task is marked as incomplete.
     *
     * @param description a string to describe the task.
     */
    public Task(String description) throws JennyException {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     * "X" to indicate completed, " " to indicate uncompleted.
     *
     * @return {@link String}
     */
    public String icon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets the status the task as complete.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the status the task as incomplete.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a comma delimited string containing data of the task.
     *
     * @return a comma delimited string.
     */
    public String save() {
        return String.format("%s,%s,%s", MESSAGE_SCOPE, this.isDone, this.description);
    }

    /**
     * Returns the description of the task as a string.
     *
     * @return the description of the task as a string.
     */
    @Override
    public String toString() {
        return "[" + icon() + "] " + this.description;
    }
}
