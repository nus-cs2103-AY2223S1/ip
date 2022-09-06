package duke.task;

/**
 * The superclass of all tasks.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    protected String getSavedStatusIcon() {
        return (isDone ? "1" : "0");
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Return a string that contains the status icon and the description of the task.
     * @return A string that contains the status icon and the description of the task.
     */
    public String taskInfo() {
        return "[] [" + getStatusIcon() + "] " + description;
    }

    /**
     * Return a string that contains the status icon and description of the task that
     * is used for saving.
     * @return A string that contains the status icon and description of the task that
     * is used for saving.
     */
    public String taskSaveInfo() {
        return "," + getSavedStatusIcon() + "," + description;
    }

    /**
     * Return true if the description of the task contains the specified keyword.
     * @param keyword The keyword to search for.
     * @return True if description contains the keyword, false otherwise.
     */
    public boolean containKeyword(String keyword) {
        return this.description.contains(keyword);
    }

}
