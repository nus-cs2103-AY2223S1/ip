package DukeBot;

public abstract class Task {

    private static int taskCount = 0;

    private String description;
    private boolean isComplete = false;

    /**
     * Constructor for Task.
     *
     * @param description description of the task.
     */
    public Task(String description) {
        this.description = description;
        Task.taskCount++;
    }

    /**
     * Get the status icon of a task.
     *
     * @return Status icon of Task.
     */
    public String getStatusIcon() {
        // mark completed task with X
        return (isComplete ? "X" : " ");
    }

    /**
     * Mark Task as complete.
     *
     * @throws DukeException if Task is already complete.
     */
    public void markComplete() throws DukeException {
        if (this.isComplete) {
            throw new DukeException("Tried to mark an already completed task.");
        }
        this.isComplete = true;
    }

    /**
     * Get task count.
     *
     * @return taskCount Number of tasks.
     */
    public static int getTaskCount() {
        return taskCount;
    }
    /**
     * Increase task count by 1.
     */
    public static void addTaskCount() {
        Task.taskCount++;
    }

    /**
     * Reduce task count by 1.
     */
    public static void reduceTaskCount() {
        Task.taskCount--;
    }

    /**
     * Mark Task as incomplete.
     *
     * @throws DukeException if Task is incomplete.
     */
    public void markIncomplete() throws DukeException {
        if (!this.isComplete) {
            throw new DukeException("Tried to unmark an incomplete task.");
        }
        this.isComplete = false;
    }

    /**
     * Get the String representation of a Task.
     *
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }


    /**
     * Get the description of the Task.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    public abstract String getTime();
    public abstract String getTaskType();
}
