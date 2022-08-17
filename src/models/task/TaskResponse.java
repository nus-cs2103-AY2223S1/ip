package models.task;

/**
 * Dataclass to represent response from model after adding or deleting tasks (to include number of tasks after operation)
 */
public class TaskResponse {
    public Task task;
    public int taskCount;

    public TaskResponse(Task task, int taskCount) {
        this.task = task;
        this.taskCount = taskCount;
    }

}

