package task;

/**
 * Dataclass to represent response from model after adding or deleting tasks (to include number of
 * tasks after operation)
 */
public class TaskResponse {
    private final Task task;
    private final int taskCount;

    /**
     * Creates new TaskResponse representing relevant task and number of tasks after an operation
     * @param task Task in response
     * @param taskCount Task count after the action
     */
    public TaskResponse(Task task, int taskCount) {
        this.task = task;
        this.taskCount = taskCount;
    }

    public Task getTask() {
        return task;
    }

    public int getTaskCount() {
        return taskCount;
    }
}

