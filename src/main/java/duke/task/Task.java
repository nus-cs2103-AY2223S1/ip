package duke.task;

/**
 * Task contains description of a task.
 */
public class Task {
    private String description;
    private boolean isDone;
    private Priority priority;

    private enum Priority {
        HIGH, MEDIUM, LOW
    }

    /**
     * Constructor for Task.
     *
     * @param description task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Constructor for Task.
     *
     * @param description task description.
     * @param priority priority of task.
     */
    public Task(String description, String priority) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.valueOf(priority);
    }

    public Priority getPriority() {
        return this.priority;
    }

    public void setPriority(String priority) {
        switch (priority) {
        case "LOW":
            this.priority = Priority.LOW;
            break;
        case "MEDIUM":
            this.priority = Priority.MEDIUM;
            break;
        case "HIGH":
            this.priority = Priority.HIGH;
            break;
        default:
            break;
        }
    }

    public String getStringPriority() {
        switch (priority) {
        case LOW:
            return "[LOW]";
        case MEDIUM:
            return "[MEDIUM]";
        case HIGH:
            return "[HIGH]";
        default:
            return "";
        }
    }

    /**
     * Returns status icon of task.
     *
     * @return status icon of task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns status of task.
     *
     * If task is done, return 1. If task is not done, return 0.
     * @return status of task.
     */
    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    /**
     * Returns task description.
     *
     * @return task description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks a task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Unmarks a task as done.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns task type of task.
     *
     * @return task type.
     */
    public String getTaskType() {
        return null;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "]"
                + this.getStringPriority() + " " + this.description;
    }
}
