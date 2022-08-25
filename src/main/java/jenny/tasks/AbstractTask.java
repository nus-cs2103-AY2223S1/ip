package jenny.tasks;

/**
 * Serves as a base class for all other different types tasks.
 * CS2103 Week 2
 * AY21/22 Semester 1
 *
 * @author Deon
 */
public abstract class AbstractTask {
    private static final String MESSAGE_SCOPE = AbstractTask.class.getSimpleName();
    protected static final String ERROR_INVALID_DESCRIPTION = "The description of this task cannot be empty.";
    protected static final String ERROR_INVALID_DUE_DATE = "The due date of this task cannot be empty.";
    protected String description;
    protected boolean isDone;

    /**
     * Creates an instance of a new task.
     *
     * @param description a string to describe the task.
     */
    public AbstractTask(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string.
     * "X" to indicate completed, " " to indicate uncompleted.
     *
     * @return the status of the task as a string.
     */
    public String icon() {
        return (this.isDone ? "X" : " ");
    }

    /**
     * Sets the status the task.
     *
     * @param isDone the status the task.
     */
    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
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
