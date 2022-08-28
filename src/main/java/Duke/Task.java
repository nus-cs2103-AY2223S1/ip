package Duke;

/**
 * Represents a task.
 */
public class Task {
    private String description;
    private boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Checks if a task is done.
     * @return String indicating whether a task is completed.
     */
    public String getStatusIcon() {
        return (isDone? "X": " ");
    }
    public void markDone() {
        this.isDone = true;
    }
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a string representation of Task.
     * @return String representation of Task.
     */
    @Override
    public String toString() {
        return "["+ getStatusIcon()+"] "+this.description;
    }
}
