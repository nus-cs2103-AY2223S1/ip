package duke.chatbot.data.task;

/**
 * A task that contains a description and a boolean
 * that indicates whether the task has been completed.
 * @author Jordan Quah Shao Xuan
 */
public abstract class Task {
    /** A description of the task */
    private final String description;

    /** A boolean describing whether the task is done */
    private boolean isDone = false;

    /**
     * Constructs a task that is not done.
     * @param description A string that describes the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructs a task that may be done depending on the isDone argument.
     * @param description A string that describes the task.
     * @param isDone A boolean value that describes whether the
     *     task is done.
     */
    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Sets the task to the done state.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Sets the task to the not done state.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string that represents whether the task is done.
     * @return A string that represents whether the task is done.
     */
    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    /**
     * Returns a string that is written onto a text file to be stored.
     * @return A string that is written onto a text file to be stored.
     */
    public String encode() {
        return ",,," + (isDone ? 1 : 0)
                + ",,," + description;
    }

    public boolean hasSubstring(String substring) {
        return description.contains(substring);
    }
}
