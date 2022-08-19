/**
 * The Task class represents a task
 * added to the storage.
 */
public class Task {
    private String description;
    private Boolean isDone;

    /**
     * Construct a Task object.
     * @param description description for Task.
     */
    public Task(String description) {
        this.isDone = false;
        this.description = description;
    }

    /**
     * Mark the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Return an icon that represents whether the task is done.
     * @return String representation of status.
     */
    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    /**
     * Overriding method of toString() for Task.
     * @return the string representing Task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
