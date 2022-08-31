package duke.taskmanager.task;

import duke.taskmanager.exceptions.EmptyTaskException;

public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    /**
     * Abstract constructor for a task with information indicating the name of the task.
     * Default completion status of the task is false.
     * To be inherited by different tasks.
     *
     * @param taskName string of the name of the task
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Abstract constructor for a task with information indicating the name of the task.
     * To be inherited by different tasks.
     *
     * @param taskName string of the name of the task
     * @param isCompleted boolean of the completion status of the task.
     */
    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public abstract boolean isEmpty();
    public abstract String getFormattedString();

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X":" ") + "] " + this.taskName;
    }
}