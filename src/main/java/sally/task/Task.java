package sally.task;

/**
 * Task class to represent generic task
 *
 * @author liviamil
 */

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for todo tasks
     *
     * @param description description for the todo task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getMoreInfo() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean getDoneStatus() {
        return this.isDone;
    }

    public String toStorage() {
        return (isDone ? "1" : "0") + " | " + this.description;
    }

    public String getOutput() {
        return String.format("O | %d | %s", isDone ? 1 : 0, description);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
