package duke.tasks;

import duke.exceptions.DukeException;

public abstract class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) throws DukeException {
        this(description, false);
    }

    public Task(String description, boolean isDone) throws DukeException {
        if (description == null || description.length() == 0) {
            throw new DukeException("Task description cannot be empty.");
        }
        this.description = description;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark the task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmark the task, ie not completed.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string to be displayed to the user after they added a new <Code>Task</Code>.
     *
     * @param numberTasks The current total number of tasks.
     * @return A confirmation message to be displayed to the user.
     */
    public String getAddMessage(int numberTasks) {
        return "Got it. I've added this task:\n" + this + "\nNow you have " + numberTasks + " tasks in the list";
    }

    /**
     * Returns a string representation of the object suitable for storage.
     *
     * @return A storage-friendly string representation.
     */
    public String encode() {
        return (this.isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * Returns a user-friendly string representation of the object.
     *
     * @return A user-friendly string representation.
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
