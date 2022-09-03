package duke.task;

import duke.DukeException;

/**
 * This class encapsulates a task set by the user.
 */
public abstract class Task implements Comparable<Task> {
    private String description;
    private boolean isDone;

    /**
     * Creates a Task with the given description.
     *
     * @param description The description for the Task.
     * @throws DukeException If the description is empty.
     */
    Task(String description) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task with the given description and completion status.
     *
     * @param description The description for the Task.
     * @param isDone      The completion status of the Task.
     * @throws DukeException If the description is empty.
     */
    Task(String description, boolean isDone) throws DukeException {
        if (description.equals("")) {
            throw new DukeException("The description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Changes the isDone status of this Task according to the given boolean.
     *
     * @param isDone The new isDone status of this Task.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
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
     * Encodes this Task into a String.
     *
     * @return The String encoded from this Task.
     */
    public String encode() {
        String status = this.isDone ? "1" : "0";
        return String.format("%s;%s",
                status,
                this.description);
    }

    /**
     * Returns the String representation of this Task.
     *
     * @return A String representing this Task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return String.format("[%s] %s",
                status,
                this.description);
    }
}
