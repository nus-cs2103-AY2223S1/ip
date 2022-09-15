package violet.task;

/**
 * The Task class encapsulates a task.
 */
public class Task {
    /** The description of the task */
    protected String description;
    /** Flag to indicate if a task is done or not */
    protected boolean isDone;
    /** Priority to indicate urgency of task */
    protected Priority priority;

    enum Priority {
        LOW,
        MEDIUM,
        HIGH
    }

    /**
     * Instantiates a Task object.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Instantiates a Task object.
     *
     * @param description Description of the task.
     * @param isDone Flag to indicate if task is done or not.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.priority = Priority.LOW;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String getPriorityStatus() {
        return this.priority.toString();
    }

    public void setPriorityStatus(String priority) {
        this.priority = Priority.valueOf(priority);
    }

    /**
     * Returns the String representation of the Task object when saved into a file.
     *
     * @return a string representation of the object.
     */
    public String fileStatus() {
        String status = this.isDone ? "1" : "0";
        return getPriorityStatus() + " | " + status + " | " + this.description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + this.getPriorityStatus() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
