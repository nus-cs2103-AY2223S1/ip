package dwuke.task;

import dwuke.DwukeException;

/**
 * This class encapsulates a task set by the user.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task with the given description.
     *
     * @param description The description for the Task.
     * @throws DwukeException If the description is empty.
     */
    Task(String description) throws DwukeException {
        if (description.equals("")) {
            throw new DwukeException("da descwiption of a twask cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task with the given description and completion status.
     *
     * @param description The description for the Task.
     * @param isDone The completion status of the Task.
     * @throws DwukeException If the description is empty.
     */
    Task(String description, boolean isDone) throws DwukeException {
        if (description.equals("")) {
            throw new DwukeException("da descwiption of a twask cannot be empty.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks this Task as done or not done, depending on the given boolean.
     *
     * @param isDone The new completion status of this Task.
     */
    public void mark(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Encodes this Task into a String.
     *
     * @return The String encoded from this Task.
     */
    public String encode() {
        return (this.isDone ? "1" : "0") + ";" + this.description;
    }

    /**
     * Returns the description of this Task.
     *
     * @return The description of this Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of this Task.
     *
     * @return A String representing this Task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.description;
    }
}
