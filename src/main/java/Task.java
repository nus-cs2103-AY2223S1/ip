public abstract class Task {
    /**
     * Specifies a task to be done.
     */

    private String taskDescription;
    private boolean completedTask;

    protected Task(String taskDescription) {
        this.taskDescription = taskDescription;
        completedTask = false;
    }

    public void markDone() {
        /**
         * Mark this specific task as done.
         */

        completedTask = true;
    }

    public void unmarkDone() {
        /**
         * Unmark this specific task to be undone.
         */

        completedTask = false;
    }

    private String getStatusIcon() {
        /**
         * Checks whether the task is marked, and update the status icon
         * to be an "X".
         */

        return (completedTask ? "X" : " ");
    }

    private String getTaskDescription() {
        /**
         * Gets description for this task.
         */

        return taskDescription;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getTaskDescription());
    }
}
