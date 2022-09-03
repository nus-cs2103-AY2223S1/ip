package duke.task;

/**
 * Represents a Task that can be described and marked as done.
 */
public abstract class Task {
    /* Description of the task. */
    protected String description;
    /* Boolean representing the completion of the task. */
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the task description.
     *
     * @return Task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the isDone property to true.
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Sets the isDone property to false.
     */
    public void unmark() {
        this.isDone = false;
    }

    /**
     * Returns a string that is safe to use with the save file.
     *
     * @return String that is of the save file format.
     */
    public abstract String saveText();

    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%s] %s", statusIcon, this.description);
    }
}