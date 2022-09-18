package duke.models;

import duke.utils.Interval;

public class Task {
    protected String description;
    protected boolean isDone;
    protected boolean isRecurring = false;
    protected Interval recurringInterval = Interval.None;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public Task(String description, boolean isDone, Interval interval) {
        this.description = description;
        this.isDone = isDone;
        this.recurringInterval = interval;
        this.isRecurring = interval != Interval.None;
    }

    /**
     * Gets the description of the Task.
     *
     * @return Task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the status icon of the current Task.
     * Task has a status icon of "X" if marked as done, and " " otherwise.
     *
     * @return Status icon of the current Task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Gets the status icon of the current Task.
     * Task has a status icon of "X" if marked as done, and " " otherwise.
     *
     * @return Status icon of the current Task.
     */
    public String getIntervalIcon() {
        return this.recurringInterval.toString();
    }

    /**
     * Gets the status icon of the current Task.
     * Task has a status icon of "X" if marked as done, and " " otherwise.
     *
     * @return Status icon of the current Task.
     */
    public Interval getInterval() {
        return this.recurringInterval;
    }

    /**
     * Gets whether the current Task is recurring.
     *
     * @return isRecurring.
     */
    public boolean getIsRecurring() {
        return this.isRecurring;
    }

    /**
     * Gets whether the current Task is done.
     *
     * @return isDone.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Marks the current Task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks the current Task as completed.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getIntervalIcon(), getStatusIcon(), this.description);
    }
}
