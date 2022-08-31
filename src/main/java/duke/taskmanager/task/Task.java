package duke.taskmanager.task;

import duke.taskmanager.exceptions.EmptyTaskException;

public abstract class Task {
    private final String taskName;
    private boolean status; // True = Done, False = Not Done

    /**
     * Abstract constructor for a task with information indicating the name of the task.
     * Default completion status of the task is false.
     * To be inherited by different tasks.
     *
     * @param taskName string of the name of the task
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    /**
     * Abstract constructor for a task with information indicating the name of the task.
     * To be inherited by different tasks.
     *
     * @param taskName string of the name of the task
     * @param status boolean of the completion status of the task.
     */
    Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract boolean isEmpty();
    public abstract String getFormattedString();

    @Override
    public String toString() {
        return "[" + (status ? "X":" ") + "] " + this.taskName;
    }
}