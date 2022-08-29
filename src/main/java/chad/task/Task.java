package chad.task;

/**
 * chad.Task is the core functionality of the Chadbot
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for task
     * @param description description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public boolean getDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
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
