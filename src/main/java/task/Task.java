package task;

import org.json.JSONObject;

/**
 * Data class to represent a Task and output a user-friendly String representation
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Superclass constructor for Task
     * @param description Description of the task
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        this.taskType = taskType;
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Superclass constructor for Task
     * @param description Description of the task
     */
    public Task(String description, TaskType taskType) {
        this(description, taskType, false);
    }


    public String getDescription() {
        return description;
    }

    protected String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    protected abstract String getTypeIndicator();

    public void markAsDone() {
        this.isDone = true;
    }
    public void unmark() {
        this.isDone = false;
    }

    protected JSONObject toJsonObject() {
        JSONObject obj = new JSONObject();
        obj.put("description", description);
        obj.put("taskType", taskType);
        obj.put("isDone", isDone);
        return obj;
    }
    protected abstract String serialize();

    @Override
    public String toString() {
        return String.format("[%s] [%s] %s", getTypeIndicator(), getStatusIcon(), description);
    }
}
