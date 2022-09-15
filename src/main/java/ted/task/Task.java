package ted.task;

/**
 * Represents a task. A <code>Task</code> object can be one of three types,
 * Todo, Deadline, or Event.
 */
public class Task {
    private static final String DONE_DISPLAY = "[X] ";
    private static final String NOT_DONE_DISPLAY = "[ ] ";
    private static final String DONE_FILEFORMAT = "1 | ";
    private static final String NOT_DONE_FILEFORMAT = "0 | ";
    private String taskDescription;
    private boolean isTaskDone;

    /**
     * Creates Task object with default not done status.
     *
     * @param taskDescription task name.
     */
    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isTaskDone = false;
    }

    /**
     * Creates Task object that is specified to be done or not done.
     *
     * @param taskDescription task name.
     * @param isTaskDone boolean indicating task's done status.
     */
    public Task(String taskDescription, boolean isTaskDone) {
        this.taskDescription = taskDescription;
        this.isTaskDone = isTaskDone;
    }

    /**
     * Indicates task as done.
     */
    public void markDone() {
        this.isTaskDone = true;
    }

    /**
     * Indicates task as undone.
     */
    public void unmarkDone() {
        this.isTaskDone = false;
    }

    /**
     * Returns Task in the correct String format for bot response.
     *
     * @return String that represents Task.
     */
    @Override
    public String toString() {
        return isTaskDone ? DONE_DISPLAY + this.taskDescription
                : NOT_DONE_DISPLAY + this.taskDescription;
    }

    /**
     * Returns Task in the correct String format to write to file.
     *
     * @return String that represents Task.
     */
    public String toFileString() {
        return isTaskDone ? DONE_FILEFORMAT + this.taskDescription
                : NOT_DONE_FILEFORMAT + this.taskDescription;
    }

    /**
     * Indicates if a task has a deadline after current time.
     *
     * @return false as a default.
     */
    public boolean shouldRemind() {
        return false;
    }

}
