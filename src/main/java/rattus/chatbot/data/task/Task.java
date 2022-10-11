package rattus.chatbot.data.task;

/**
 * A task that contains a description and a boolean that indicates whether the task has been completed.
 *
 * @author jq1836
 */
public abstract class Task {
    private final String description;

    private boolean isDone = false;

    /**
     * Constructs a task that is not done.
     *
     * @param description A string that describes the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Constructs a task that may be done depending on the isDone argument.
     *
     * @param description A string that describes the task.
     * @param isDone      A boolean value that describes whether the task is done.
     */
    public Task(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /**
     * Sets the task to the done state.
     */
    public void markDone() {
        isDone = true;
    }

    /**
     * Sets the task to the not done state.
     */
    public void markUndone() {
        isDone = false;
    }

    /**
     * Returns a string that represents whether the task is done.
     *
     * @return A string that represents whether the task is done.
     */
    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return String.format("%s %s", getStatusIcon(), description);
    }

    /**
     * Returns a string that is written onto a text file to be stored.
     *
     * @return A string that is written onto a text file to be stored.
     */
    public String encode() {
        return String.format("`%d`%s`", isDone ? 1 : 0, description);
    }

    /**
     * Returns true if description contains substring.
     *
     * @param substring Substring to check description for.
     * @return True if description contains substring.
     */
    public boolean hasSubstring(String substring) {
        return description.contains(substring);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task task = (Task) obj;
        boolean isSameIsDone = task.isDone == isDone;
        boolean isSameDescription = task.description.equals(description);
        return isSameIsDone && isSameDescription;
    }
}
