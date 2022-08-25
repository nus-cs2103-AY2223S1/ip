package duke.task;

import duke.DukeException;

/**
 * This (abstract) class encapsulates a user task, consisting of the Task description and
 * its state of completion.
 * <p>
 * This abstract class provides the skeletal implementation of a user task
 * and should be the superclass of all user tasks.
 */
public abstract class Task {
    private static final String NO_DESC_MESSAGE = "The description of a Task must not be empty.";

    /** A description of the user task. */
    protected String desc;

    /** The state of completion of the user task. */
    protected boolean isDone;

    /**
     * Constructs a not completed Task with the specified description parameter.
     *
     * @param desc the specified String parameter.
     * @throws DukeException if desc is null or an empty string.
     */
    public Task(String desc) throws DukeException {
        if (desc == null || desc.equals("")) {
            throw new DukeException(Task.NO_DESC_MESSAGE);
        }
        this.desc = desc;
        this.isDone = false;
    }

    /**
     * Constructs a Task with the specified description parameter and completion
     * state parameter.
     *
     * @param desc the specified task description parameter.
     * @param isDone the state of completion of this Task.
     * @throws DukeException if desc is null or an empty string.
     */
    public Task(String desc, boolean isDone) throws DukeException {
        if (desc == null || desc.equals("")) {
            throw new DukeException(Task.NO_DESC_MESSAGE);
        }
        this.desc = desc;
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the completion state of this Task.
     *
     * @return a String.
     */
    public String getStatusIcon() {
        return (this.isDone ? "[✔]" : "[ ]");
    }

    /**
     * Sets the completion state of this task with the specified Boolean parameter.
     *
     * @param isDone the specified Boolean parameter.
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the completion state of this Task, represented as a boolean.
     *
     * @return true if and only if this Task is complete.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns a String representation of this Task.
     *
     * @return a String.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.desc;
    }

    /**
     * Returns a String representation of this Task, to be used for file storage.
     *
     * @return a String.
     */
    public String toStorageString() {
        return String.format("%s / %s", this.desc, this.isDone);
    }

    /**
     * Returns true if and only if the provided keyword exists as a substring in the Task description.
     *
     * @param keyword the substring to search for
     * @return true if the Task description contains keyword, false otherwise
     */
    public boolean hasSubstring(String keyword) {
        return this.desc.contains(keyword);
    }

}
