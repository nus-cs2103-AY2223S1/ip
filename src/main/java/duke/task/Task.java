package duke.task;

import duke.exception.DukeException;

public class Task {
    private static final String DONE_STATUS = "X";
    private static final String NOT_DONE_STATUS = " ";
    private static final String DONE_STORAGE = "1";
    private static final String NOT_DONE_STORAGE = "0";
    protected static final String STORAGE_DELIMITER = " | ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? DONE_STATUS : NOT_DONE_STATUS);
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }


    public String toStorageString() {
        String statusStorage = this.isDone ? DONE_STORAGE : NOT_DONE_STORAGE;
        return statusStorage + Task.STORAGE_DELIMITER + this.description;
    }

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
}