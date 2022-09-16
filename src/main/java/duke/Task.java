package duke;

/**
 * Represents a Task with description, boolean to set the Task as done or not done, and type (ToDo = 'T',
 * Deadline = 'D', Event = 'E').
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    /**
     * Constructor of Task with description.
     *
     * @param description Description of the Task.
     */
    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    /**
     * Constructor of Task with description and boolean to set the Task as done or not done.
     *
     * @param description Description of the Task.
     * @param isDone Boolean to set the Task as done or not done.
     */
    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Marks Task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks Task as not done.
     */
    public void markNotDone() {
        isDone = false;
    }

    /**
     * Returns the status icon of the Task, 'X' if done and ' ' if not done.
     *
     * @return Status icon of the Task, 'X' if done and ' ' if not done.
     */
    public char getStatusIcon() {
        return isDone ? 'X' : ' '; // done = X
    }

    /**
     * Returns the String representation of the Task for UI.
     *
     * @return String representation of the Task for UI.
     */
    @Override
    public String toString() {
        return String.format("[%c][%c] %s", type, getStatusIcon(), description);
    }

    /**
     * Returns the String representation of the Task for Storage.
     *
     * @return String representation of the Task for Storage.
     */
    public String toData() {
        return String.format("%c, %c, %s", type,  getStatusIcon(), description);
    }
}
