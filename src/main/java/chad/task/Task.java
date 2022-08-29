package chad.task;

/**
 * chad.Task is the core functionality of the Chadbot
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    public Task(String description, boolean done) {
        this.description = description;
        this.isDone = done;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    /**
     * Set task done attribute to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set task done attribute to false
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
