package qoobee;

/**
 * Represents a base task.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected String priorityLevel;

    /**
     * Creates a task given a description.
     * @param description The details of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        //default priority
        this.priorityLevel = "NA";
    }

    /**
     * Returns the status icon as marked or unmarked.
     * @return The status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the details of the task.
     * @return The details of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the String representation of the task.
     * @return The String representation of the task.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Sets the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    public String storageToString() {
        return "";
    }

    /**
     * Sets the priority level of the task.
     * @param priorityLevel The priority level represented as low, medium or high.
     */
    public void setPriorityLevel(String priorityLevel) throws QoobeeException {
        String priority = priorityLevel.toLowerCase();
        switch (priority) {
        case "low":
            this.priorityLevel = "LOW";
            break;
        case "medium":
            this.priorityLevel = "MEDIUM";
            break;
        case "high":
            this.priorityLevel = "HIGH";
            break;
        case "na":
            this.priorityLevel = "NA";
            break;
        default:
            throw new QoobeeException("Please enter a valid priority (low,medium,high)");
        }
    }

    /**
     * Gets the priority level of the task.
     * @return The String representation of the priority level.
     */
    public String getPriorityLevel() {
        return this.priorityLevel;
    }

}
