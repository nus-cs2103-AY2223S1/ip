package duke.task;

import duke.exception.DukeException;


/**
 * Encapsulates a task with a given description and completion status.
 */
public class Task {
    protected static final String STORAGE_DELIMITER = " | ";
    private static final String DONE_STATUS = "X";
    private static final String NOT_DONE_STATUS = " ";
    private static final String STORAGE_DONE_STATUS = "1";
    private static final String STORAGE_NOT_DONE_STATUS = "0";

    protected String description;
    protected boolean isDone;



    /**
     * Creates a Task instance.
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the completion status of the task.
     * @return String representation of th completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? Task.DONE_STATUS : Task.NOT_DONE_STATUS);
    }

    /**
     * Mark the Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Mark the Task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of Task to be stored.
     * @return A String representation of Task to be stored in Storage.
     */
    public String toStorageString() {
        String statusInStorage = this.isDone ? Task.STORAGE_DONE_STATUS : Task.STORAGE_NOT_DONE_STATUS;
        return statusInStorage + Task.STORAGE_DELIMITER + this.description;
    }

    /**
     * Create and returns a Task object from a storage string.
     * @param storageString A storage string representing the Task.
     * @return Task represented by the storage string.
     */
    public static Task fromStorageString(String storageString) {
        String[] storageStringArray = storageString.split(" \\| ");
        TaskType taskType = TaskType.parseToTaskType(storageStringArray[0]);
        Task task;

        switch (taskType) {
        case TODO:
            task = new Todo(storageStringArray[2]);
            break;
        case EVENT:
            try {
                task = new Event(storageStringArray[2], storageStringArray[3]);
            } catch (DukeException exception) {
                throw new RuntimeException(exception.getMessage());
            }
            break;
        case DEADLINE:
            try {
                task = new Deadline(storageStringArray[2], storageStringArray[3]);
            } catch (DukeException exception) {
                throw new RuntimeException(exception.getMessage());
            }
            break;
        default:
            throw new RuntimeException(String.format("Invalid task type %s.", taskType));
        }
        if (storageStringArray[1].equals(Task.STORAGE_DONE_STATUS)) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Checks if the description of the Task contains the keyword.
     * @param keyword The word to be matched with.
     * @return a boolean that indicates if the description of the Task contains the keyword.
     */
    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }

    /**
     * Returns the string representation of Task to be displayed.
     * @return A String representation of Task to be displayed.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
