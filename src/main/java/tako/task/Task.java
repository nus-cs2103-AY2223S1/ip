package tako.task;

/**
 * Represents a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for a task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if the task is done already.
     *
     * @return "X" if the task is done, else " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }


    /**
     * Converts this task to a suitable format for file storage.
     *
     * @return String representing the task in file format.
     */
    public String convertToFileFormat() {
        int taskDoneStatus = isDone ? 1 : 0;
        return String.format("T | %d | %s", taskDoneStatus, description);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
