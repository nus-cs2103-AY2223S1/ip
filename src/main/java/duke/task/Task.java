package duke.task;

/**
 * Abstract class for all types of tasks.
 */
public abstract class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Outputs as a String to be printed by UI.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }

    /**
     * Outputs as a String to be saved by Storage.
     */
    public String getSaveFormat() {
        return String.format("%d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public int compareTo(Task other) {
        if (isDone != other.isDone) {
            return Boolean.compare(isDone, other.isDone);
        }
        return description.compareTo(other.description);
    }
}
