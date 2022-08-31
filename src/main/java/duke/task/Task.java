package duke.task;

public abstract class Task {
    private String taskName;
    private boolean isDone;

    /**
     * Returns a Task object.
     * Initialises the Task with taskName variable and marks the Task as not done initially.
     *
     * @param taskName The name given to the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    /**
     * Sets the status of the task according to the boolean parameter.
     *
     * @param isDone True to mark task as done and false to mark task as not done
     */
    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the name of the task.
     *
     * @return Name of the task.
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Returns a boolean on whether the task is done.
     *
     * @return True if the task is done, false if the task is not done.
     */
    public Boolean getTaskStatus() {
        return this.isDone;
    }

    /**
     * Returns the string representation of the task to be printed to user.
     *
     * @return String representation of the task to be printed to user.
     */
    @Override
    public String toString() {
        return (this.getTaskStatus() ? "[X]" : "[ ]") + " " + this.getTaskName();
    }

    /**
     * Abstract method that returns the string representation of the task that is used to save into text file
     *
     * @return String representation of the task that is used to save into text file.
     */
    public abstract String toFileString();
}
