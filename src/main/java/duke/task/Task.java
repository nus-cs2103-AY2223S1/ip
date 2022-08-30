package duke.task;

import duke.exception.DukeException;

/**
 * Represents a generic task.
 */
public abstract class Task {
    public static final String STATUS_DONE = "[X]";
    public static final String STATUS_NOT_DONE = "[ ]";
    public static final String DONE_STORAGE = "1";
    public static final String NOT_DONE_STORAGE = "0";
    public static final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the String representation of the completion status.
     *
     * @return String representation of the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? STATUS_DONE : STATUS_NOT_DONE); // mark done task with X
    }

    /**
     * Returns the String representation of the completion status for storage.
     * .
     * @return String representation of the completion status for storage.
     */
    public String getStorageStatusIcon() {
        return (isDone ? DONE_STORAGE : NOT_DONE_STORAGE);
    }

    /**
     * Returns the String representation of the Task.
     *
     * @return String representation of the Task.
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    /**
     * Returns the String representation of the Task for storage.
     *
     * @return String representation of the Task for storage.
     */
    public String toStorage() {
        return SEPARATOR + this.getStorageStatusIcon() + SEPARATOR + this.description;
    }

    /**
     * Marks the task as done.
     *
     * @return String representation of the Task.
     */
    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    /**
     * Marks the task as not done.
     *
     * @return String representation of the Task.
     */
    public String markAsUndone() {
        this.isDone = false;
        return this.toString();
    }

    /**
     * Verifies whether the Task description contains the given keyword.
     *
     * @param content Given keyword.
     * @return true if it contains the given keyword, false otherwise.
     */
    public boolean contains(String content) {
        return this.description.contains(content);
    }

    /**
     * Constructs a Task based on the corresponding storage String.
     *
     * @param task String representation of the Task for storage.
     * @return Task corresponding to the storage String.
     * @throws DukeException If the storage String is invalid.
     */
    public static Task fromStorage(String task) throws DukeException {
        String[] taskDetails = task.split(" \\| ");
        TaskType taskType = TaskType.parse(taskDetails[0]);
        Task currTask;
        switch (taskType) {
        case DEADLINE:
            if (taskDetails.length < 4) {
                throw new DukeException("Invalid task description!");
            }
            currTask = new Deadline(taskDetails[2], taskDetails[3]);
            break;
        case EVENT:
            if (taskDetails.length < 4) {
                throw new DukeException("Invalid task description!");
            }
            currTask = new Event(taskDetails[2], taskDetails[3]);
            break;
        case TODO:
            if (taskDetails.length < 3) {
                throw new DukeException("Invalid task description!");
            }
            currTask = new ToDo(taskDetails[2]);
            break;
        default:
            throw new DukeException("Invalid task type!");
        }
        if (taskDetails[1].equals(DONE_STORAGE)) {
            currTask.markAsDone();
        }
        return currTask;
    }
}
