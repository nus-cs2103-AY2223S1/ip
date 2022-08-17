/**
 * The Task class is an abstract class which is inherited by
 * Todo, Deadline and Event.
 * @author Sheryl-Lynn Tan (G11)
 */
public abstract class Task {
    /**
     * Task name as a string.
     */
    protected String description;

    /**
     * Task name as a string.
     */
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description of task name.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * This method sets the task as completed.
     */
    public void setCompleted() {
        this.isDone = true;
    }

    /**
     * This method sets the task as incomplete.
     */
    public void setIncomplete() {
        this.isDone = false;
    }

    /**
     * This method returns the string if a task is complete or not.
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }
}