package duke.tasks;

import java.time.format.DateTimeParseException;

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

    public boolean hasDescription(String description) {
        return this.description.toLowerCase().contains(description.toLowerCase());
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
     * Returns string representation of task.
     *
     * @return string representation of task
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
