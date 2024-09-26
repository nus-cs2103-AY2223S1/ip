package jarvis.task;

/**
 * Represents a Task.
 */
public abstract class Task {
    private static int count = 0;
    private String description;
    private boolean isDone;

    /**
     * Returns a Task object with the given Description.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        count++;

    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Get done/undone status of task
     * @return "X" if done, " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * @return String description of Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public static int getCount() {
        return count;
    }

    public static void minusCount() {
        count--;
    }
}
