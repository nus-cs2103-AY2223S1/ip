package duke;

/**
 * Represents a task that a user has
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * A constructor for the Task class
     *
     * @param description The description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Obtains the status of the task
     *
     * @return An 'X' if the task is done; otherwise a whitespace if the task is not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Obtains the status of the task in numerical value
     *
     * @return A '1' if the task is done; otherwise a '0' if the task is not done
     */
    public int getStatusNumber() {
        return (isDone ? 1 : 0);
    }

    /**
     * Obtains the description of the task
     *
     * @return The description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the status of the task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the status of the task to be not done
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Obtains the details of the task
     *
     * @return The description and status of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Sets the datetime of the task
     * Note that the task must be an Event or a Deadline in order to use this method
     * Otherwise, the method will throw an exception by default
     *
     * @param dt The datetime to set on the task
     * @throws DukeException if the task is not an Event or Deadline
     */
    public String setDatetime(String dt) throws DukeException {
        throw new DukeException("This task does not have a datetime!");
    }
}
