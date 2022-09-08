package poolsheen.task;

import java.time.LocalDate;

/**
 * Class for the details of a task that Poolsheen can remember.
 */
public abstract class Task {

    /** If the task is finished. */
    protected boolean isDone;

    /** The time for the task. */
    protected LocalDate time;

    /** The details of the task. */
    private String description;

    /**
     * A protected constructor for Tasks.
     *
     * @param description The description of the task.
     * @param isDone If the task is done or not.
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the status of the task.
     *
     * @return A string of either "X" or "-".
     */
    public String getStatusIcon() {
        // mark done task with X
        return isDone ? "X" : "-";
    }

    /**
     * Returns the description of the task.
     * @return A string containing the task's description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets this task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Sets this task's description to a new description.
     * @param newDesc The new description of the task.
     */
    public void setDesc(String newDesc) {
        this.description = newDesc;
    }

    /**
     * Sets this task's time to a new time.
     * @param newTime The new time of the task.
     */
    public abstract void setTime(String newTime);

    /**
     * Returns an array of strings which can be written into the save file.
     *
     * @return An array of strings.
     */
    public abstract String[] toArr();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
