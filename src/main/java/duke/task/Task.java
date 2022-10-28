package duke.task;

/**
 * Stores the description of a task and whether it is done.
 */
public abstract class Task {
    private static final String DONE = "X";
    private static final String NOT_DONE = " ";
    private String description;
    private boolean isDone;

    /**
     * Initialises the task object.
     *
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initialises the task object.
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the boolean isDone of the task.
     *
     * @return isDone
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the description of the task.
     *
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtains the status icon.
     *
     * @return statusIcon
     */
    public String getStatusIcon() {
        return (isDone ? DONE : NOT_DONE);
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method to mark task not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the task as a string.
     *
     * @return string
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), description);
    }

    /**
     * Returns the task for saving and loading.
     *
     * @return strArray
     */
    public String[] getPrintRepresentation() {
        return new String[] { "Task", description, String.valueOf(isDone) };
    }
}
