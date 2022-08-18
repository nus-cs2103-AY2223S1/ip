package models;

/**
 * Task class with parameters of description and isDone state
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task object with a description
     * @param description A description detailing the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Sets the isDone value of this task to true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the isDone value of this task to false
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public abstract String getSymbol();
    public abstract String getDescription();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
