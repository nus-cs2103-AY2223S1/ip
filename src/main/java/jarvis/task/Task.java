package jarvis.task;

/**
 * Contains the information that todo, event, deadline have in common
 */
public abstract class Task {
    public static enum TaskType {
        ToDo,
        Deadline,
        Event
    }
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Get icon of whether the task is done
     * @return "X" if done and " " if not done
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUnDone() {
        this.isDone = false;
    }

    public abstract TaskType getTaskType();

    public abstract String toDataForm();
}
