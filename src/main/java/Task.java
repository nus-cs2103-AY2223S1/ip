/**
 * Class that represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     *
     * @param description a String that represents the Task description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Method that marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Method that marks Task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns a String that represents the Task.
     *
     * @return a String that represents the Task
     */
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}

