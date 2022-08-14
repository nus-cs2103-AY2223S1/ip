/**
 * This class encapsulates a task set by the user.
 */
public class Task {
    private String content;
    private boolean isDone;

    Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /**
     * Marks this task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a String representation of this task.
     *
     * @return A String representing this task.
     */
    @Override
    public String toString() {
        String status = this.isDone ? "X" : " ";
        return "[" + status + "] " + this.content;
    }
}
