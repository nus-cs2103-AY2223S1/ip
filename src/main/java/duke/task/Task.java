package duke.task;

import java.time.LocalDate;

/**
 * An abstract class that encapsulates the task.
 */
public abstract class Task implements Comparable<Task> {
    /** The description of the task */
    protected String description;
    /** The completion status of the task */
    protected boolean isDone;

    /**
     * The class constructor.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets the completion status icon of the task.
     *
     * @return "X" if the task is completed and " "
     *             if the task is not completed.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the current task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the current task as not done.
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string to be saved in the text file to be
     * loaded in later.
     *
     * @return The string identifying the details of the task.
     */
    public abstract String stringToSave();

    /**
     * Returns the description.
     *
     * @return The description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the date associated with the task.
     *
     * @return The date associated with the task.
     */
    public abstract LocalDate getDate();

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
