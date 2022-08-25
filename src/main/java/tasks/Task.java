package tasks;

import util.ParsedData;

/**
 * This class encapsulates a Task.
 */
public abstract class Task {
    protected String description;
    protected Boolean isDone;

    /**
     * Constructor for Deadline
     *
     * @param parsedData Data used to create the Task.
     */
    public Task(ParsedData parsedData) {
        this.description = parsedData.getTaskName();
        this.isDone = parsedData.getStatus();
    }

    /**
     * Returns the status of the task
     *
     * @return boolean representing the status
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets the status to done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Sets the status to undone.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the status icon
     *
     * @return The status icon
     */
    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Returns the status letter
     *
     * @return The status letter.
     */
    public String getStatusLetter() {
        return isDone ? "X" : " ";
    }

    public abstract String getTypeIcon();

    public abstract String getTypeLetter();

    public abstract String getDuring();

    public abstract String getTimeText();

    /**
     * Returns the task description
     *
     * @return The task description.
     */
    public String getDescription() {
        return this.description;
    }
}
