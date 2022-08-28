package bob;

/**
 * Represents Task object, something for the user to take note of
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task object with description
     *
     * @param description name or details of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Retrieves status of completion of task
     *
     * @return "[X]" if task is done and "[ ]" if task is not
     */
    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

    /**
     * Marks or Unmarks task depending on boolean input
     *
     * @param x boolean value indicating if task should be marked as completed or not
     */
    public void toMark(boolean x) {
        this.isDone = x;
    }

    /**
     * Returns the save format of the Task object
     *
     * @return String representing how Task object is saved
     */
    public String toSave() {
        if (isDone) {
            return "1 | " + this.description;
        } else {
            return "0 | " + this.description;
        }
    }

    /**
     * Returns the string representation of the Task object
     *
     * @return String representation of Task object
     */
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
