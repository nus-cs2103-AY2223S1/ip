package dwuke.task;

import dwuke.DwukeException;

/**
 * This class encapsulates a task set by the user.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    Task(String description) throws DwukeException {
        if (description.equals("")) {
            throw new DwukeException("da descwiption of a twask cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) throws DwukeException {
        if (description.equals("")) {
            throw new DwukeException("da descwiption of a twask cannot be empty.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Encodes the Task into a String.
     *
     * @return The Task encoded as String.
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
     * Returns a String representation of this task.
     *
     * @return A String representing this task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.description;
    }
}
