package yilia.task;

/**
 * Represents an abstract task.
 */
public class Task {
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
    @Override
    public String toString() {
        return "[" + (isDone ? 'X' : ' ') + "] " + content;
    }
    public String parse() {
        return content;
    }
}
