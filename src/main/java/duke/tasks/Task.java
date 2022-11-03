package duke.tasks;

import java.time.format.DateTimeParseException;


/**
 * Represents task with description and status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor specifying description of task.
     *
     * @param description the description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns string representation of done status.
     *
     * @return string representation of done status
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Checks if task contains given description
     *
     * @param desc the specified description to check for
     * @return true if task contains specified description; false otherwise
     */
    public boolean hasDescription(String desc) {
        return this.description.toLowerCase().contains(desc.toLowerCase());
    }

    /**
     * Checks if task is before given deadline
     *
     * @param deadline the specified deadline to check for
     * @return true if task is before specified deadline; false otherwise
     * @throws DateTimeParseException if specified deadline does not follow d/mm/YYYY format
     */
    public boolean isBefore(String deadline) throws DateTimeParseException {
        return false;
    }

    /**
     * Checks if task contains duplicate description
     *
     * @param desc the specified description to check for
     * @return true if task contains duplicate description; false otherwise
     */
    public boolean isSame(String desc) {
        return desc.equals(this.description);
    }

    /**
     * Returns string representation of task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
