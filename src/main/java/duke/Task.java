package duke;

/**
 * This class is responsible for creating and manipulating a task
 *
 * @author Kang Zong Xian
 */
public abstract class Task {

    // Attributes of a duke.Task
    protected String description;
    private boolean isDone;

    /**
     * The constructor for the duke.Task
     * @param description the text for the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Get the description of the Task
     * @return a string representing the description of the task
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set isDone parameter to be true
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Set isDone parameter to be false
     */
    public void markNotDone() {
        this.isDone = false;
    }

    /**
     * Get the status of whether the task is done or not
     * @return a string representing whether the task is done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Get a statement representing if the task is already done or not
     * @return a string representing the task and its status
     */
    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        }
        return "[] " + this.description;
    }

    /**
     * Saves the task to the storage
     * @return a string representation of the task to be saved
     */
    public abstract String saveToDisk();
}
