package duke.task;

import duke.DukeException;

/**
 * Represents a task.
 */
public class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor method for a task.
     *
     * @param description description of the task
     */
    public Task(String description) throws DukeException {
        if (description.length() == 0) {
            throw new DukeException("Oops, the description of a task cannot be empty.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.getDescription();
    }

    /**
     * Returns the status icon of the task.
     *
     * @return status icon of the task
     */
    public String getStatusIcon() {
        return ("["
                + (this.isDone ? "X" : " ")
                + "]");
    }

    /**
     * Returns the task description.
     *
     * @return description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as done or not done.
     *
     * @param isDone true if the task should be marked as done, false if marked as not done
     */
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task to be saved.
     * @return string representation of the task to be saved
     */
    public String save() {
        return " | "
                + (this.isDone ? "X" : " ")
                + " | " + this.description;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}
