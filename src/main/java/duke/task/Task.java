package duke.task;

import duke.exception.DukeException;

public abstract class Task {
    private static final String DONE = "[X]";
    private static final String NOT_DONE = "[ ]";
    private static final String DONE_STORAGE = "1";
    private static final String NOT_DONE_STORAGE ="0";
    protected static final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? DONE : NOT_DONE); // mark done task with X
    }

    public String getStorageStatusIcon() {
        return (isDone ? DONE_STORAGE : NOT_DONE_STORAGE);
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }

    public String toStorage() {
        return SEPARATOR + this.getStorageStatusIcon() + SEPARATOR + this.description;
    }

    public String markAsDone() {
        this.isDone = true;
        return this.toString();
    }

    public String markAsUndone() {
        this.isDone = false;
        return this.toString();
    }

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
