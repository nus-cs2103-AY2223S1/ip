package duke.task;

import duke.exception.DukeException;

public class Task {
    private static final String DONE_STATUS = "X";
    private static final String NOT_DONE_STATUS = " ";
    private static final String STORAGE_DONE_STATUS = "1";
    private static final String STORAGE_NOT_DONE_STATUS = "0";
    protected static final String STORAGE_DELIMITER = " | ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? Task.DONE_STATUS: Task.NOT_DONE_STATUS);
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Convert to storage string format
     */
    public String toStorageString() {
        String statusInStorage = this.isDone ? Task.STORAGE_DONE_STATUS : Task.STORAGE_NOT_DONE_STATUS;
        return statusInStorage + Task.STORAGE_DELIMITER + this.description;
    }

    /**
     * Convert from storage string format
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




    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

}
