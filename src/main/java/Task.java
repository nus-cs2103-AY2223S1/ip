/**
 * Class to represent the tasks.
 */
public class Task {
    private String description;
    private Boolean isDone;

    /**
     * The constructor for task.
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * The method to get the current status.
     * @return String
     */
    public String getStatus() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * The method to change the current status.
     * @return Task object
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * The method to change the current status.
     * @return Task object
     */
    public Task markUndone() {
        this.isDone = false;
        return this;
    }

    /**
     * Getter method of description.
     * @return String
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Overridden toString method for task details
     * @return String
     */
    @Override
    public String toString() {
        return this.getStatus()  + this.description;
    }
}
