package poolsheen.task;

import java.time.LocalDate;

/**
 * Class for the details of a task that Poolsheen can remember.
 */
public abstract class Task {
    /** The details of the task. */
    public String description;

    /** If the task is finished. */
    protected boolean isDone;

    /** The time for the task. */
    protected LocalDate time;

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