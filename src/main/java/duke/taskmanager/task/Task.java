package duke.taskmanager.task;

/**
 * Task is am abstract class to be inherited by other types of tasks.
 */
public abstract class Task {
    private final String taskType;
    private String taskName;
    private boolean isCompleted;

    /**
     * Abstract constructor for a task with information indicating the name of the task.
     * Default completion status of the task is false.
     * To be inherited by different tasks.
     *
     * @param taskType string of the type of task
     * @param taskName string of the name of the task
     */
    Task(String taskType, String taskName) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Abstract constructor for a task with information indicating the name of the task.
     * To be inherited by different tasks.
     *
     * @param taskType string of the type of task
     * @param taskName string of the name of the task
     * @param isCompleted boolean of the completion status of the task.
     */
    Task(String taskType, String taskName, boolean isCompleted) {
        this.taskType = taskType;
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getTaskType() {
        return this.taskType;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    protected void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setCompleted() {
        this.isCompleted = true;
    }

    public void setNotCompleted() {
        this.isCompleted = false;
    }

    /**
     * Updates the task with the new task with updated attributes
     *
     * @param newTask the new task to update the current task with
     */
    public void update(Task newTask) {
        this.taskName = newTask.getTaskName();
        this.isCompleted = newTask.isCompleted();
    };

    /**
     * Formats the details of the task into a format that can be read and written by
     * the task manager.
     *
     * @return details of the task in a string format readable and writable by taskManager
     */
    public String getFormattedString(String attributeSeparator) {
        return getTaskType() + attributeSeparator + (isCompleted() ? 1 : 0) + attributeSeparator + getTaskName();
    }

    /**
     * Returns the details of the task to be displayed by the chatbot.
     *
     * @return details of the task
     */
    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + (isCompleted() ? "X" : " ") + "] " + getTaskName();
    }
}
