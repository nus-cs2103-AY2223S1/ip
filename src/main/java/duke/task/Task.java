package duke.task;

/**
 * Represents a Task
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task object
     */
    protected Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates a Task object
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    private String getStatus() {
        return (this.isDone ? "X" : " ");
    }

    private String getStatusData() {
        return (this.isDone ? "1" : "0");
    }


    /**
     * Returns a string describing the Task object
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.description;
    }

    /**
     * Returns a string describging the Task object
     * to be used when saving to task file
     */
    public String toStringData() {
        return getStatusData() + " | " + this.description;
    }
}
