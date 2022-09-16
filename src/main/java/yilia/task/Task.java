package yilia.task;

/**
 * Represents an abstract task.
 */
public abstract class Task {
    private boolean isDone = false;
    private final String content;
    public Task(String content) {
        this.content = content;
    }
    /**
     * Class constructor specifying the content and whether it is done.
     */
    public Task(String content, boolean isDone) {
        this(content);
        this.isDone = isDone;
    }
    /**
     * Sets the task to be done.
     */
    public void setDone() {
        isDone = true;
    }
    /**
     * Sets the task to be not done.
     */
    public void setNotDone() {
        isDone = false;
    }
    /**
     * Returns whether the task is done.
     *
     * @return The isDone value.
     */
    public boolean status() {
        return isDone;
    }
    /**
     * Returns the String representation.
     *
     * @return The String representation.
     */
    @Override
    public String toString() {
        return "[" + (isDone ? 'X' : ' ') + "] " + content;
    }
    /**
     * Returns how the task should appear on a file.
     *
     * @return The text information.
     */
    public String parse() {
        return content;
    }
    /**
     * Checks if two tasks are the same.
     *
     * @param obj The other object to compare with.
     * @return A boolean value indicating if two tasks are the same.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        return this.content.equals(((Task) obj).content);
    }
    /**
     * Returns how the task should appear on a file.
     *
     * @param content A substring that we want to know whether is contained.
     * @return The text information.
     */
    public boolean contains(String content) {
        return this.content.contains(content);
    }
}
