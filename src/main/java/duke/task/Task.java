package duke.task;

import duke.exception.DukeException;

/**
 * Task encapsulates a task with a description and a completion status.
 */
public class Task {
    protected static final String STORAGE_DELIMITER = " | ";
    private static final String DONE_STATUS = "X";
    private static final String NOT_DONE_STATUS = " ";
    private static final String DONE_STORAGE = "1";
    private static final String NOT_DONE_STORAGE = "0";
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Returns the String representation of completion status.
     *
     * @return String representation of the completion status.
     */
    public String getStatusIcon() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    /**
     * Returns the String representation of the ToDo.
     *
     * @return String representation of the ToDo.
     */
    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    /**
     * Marks the Task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the Task as uncompleted.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the String representation to be stored.
     *
     * @return storage String representation of the ToDo.
     */
    public String toStorageString() {
        String statusStorage = this.isDone ? DONE_STORAGE : NOT_DONE_STORAGE;
        return statusStorage + Task.STORAGE_DELIMITER + this.description;
    }

    /**
     * Creates a Task object from a storage String.
     *
     * @param string The Storage String representing the Task.
     * @return The Task represented by the Storage String.
     */
    public static Task fromStorageString(String string) {
        String[] taskSubstrings = string.split(" \\| ");
        TaskType taskType = TaskType.parse(taskSubstrings[0]);
        Task task;

        switch (taskType) {
        case DEADLINE:
            try {
                task = new Deadline(taskSubstrings[2], taskSubstrings[3]);
            } catch (DukeException e) {
                throw new RuntimeException(e.getMessage());
            }
            break;
        case EVENT:
            try {
                task = new Event(taskSubstrings[2], taskSubstrings[3]);
            } catch (DukeException e) {
                throw new RuntimeException(e.getMessage());
            }
            break;
        case TODO:
            task = new ToDo(taskSubstrings[2]);
            break;
        default:
            throw new RuntimeException(String.format("Invalid task type %s.", taskType));
        }

        if (taskSubstrings[1].equals(Task.DONE_STORAGE)) {
            task.isDone = true;
        }
        return task;
    }

    /**
     * Checks whether the Task description matches the given keyword.
     *
     * @param keyword The keyword to match.
     * @return Boolean indicating whether the description contains the keyword.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
