package duke.task;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

     protected Task(String description) {
         this.description = description;
         this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String encodeToString();
}
