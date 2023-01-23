package duke.task;

import duke.DukeException;

import java.time.LocalDate;

/**
 * The Task class represents a task containing a description and its state of completion.
 */
public abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    /**
     * Initializes an instance of a Task with the specified task description.
     *
     * @param taskDescription Specified task description.
     */
    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    /**
     * Sets the status of completion of the task.
     *
     * @param isDone The value to set the status of completion of the task.
     */
    public void isDoneSetter(boolean isDone) {
        this.isDone = isDone;
    }

    public boolean containKeyword(String keyword) {
        return this.taskDescription.toLowerCase().contains(keyword.toLowerCase());
    }

    /**
     * Returns the icon of the type of the task in string format.
     *
     * @return String representation of the icon of the type of the task.
     */
    protected abstract String getTypeIcon();

    /**
     * Gets the date of the task.
     *
     * @return The date of the task.
     */
    public abstract LocalDate getDate() throws DukeException;


    /**
     * Returns the icon of the completion state of the task in string format.
     *
     * @return String representation of the icon of the completion state of the task.
     */
    protected String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    /**
     * Returns a string representation of the task in a specific format for storage.
     *
     * @return String representation of the task to store in a file.
     */
    public String toStorageString() {
        return (isDone ? "1" : "0") + "|" + taskDescription;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + taskDescription;
    }
}
