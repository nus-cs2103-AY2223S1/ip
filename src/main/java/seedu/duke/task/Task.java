package seedu.duke.task;

import java.time.LocalDate;

/**
 * A Task class representing a task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object.
     *
     * @param description The description the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object.
     *
     * @param description The description of the task.
     * @param isDone Whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns "X" if the task is done, " " otherwise.
     * @return "X" if the task is done, " " otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns a string representation of the Task object.
     *
     * @return Returns a string in the format of [X] do this
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    /**
     * Returns a string representation of a Task object for the purpose of writing to a file.
     *
     * @return Returns a string in the format of 1 , do this.
     */
    public String toFileString() {
        int isDone = this.isDone ? 1 : 0;
        return isDone + " , " + description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateAndTime(LocalDate dateAndTime) {};
}
