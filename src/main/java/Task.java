public class Task {
    /**
     * Specifies a task to be done.
     */

    private String taskDescription;
    private boolean completedTask;

    public Task(String taskDescription, boolean completedTask) {
        this.taskDescription = taskDescription;
        this.completedTask = completedTask;
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

    public boolean isDone() {
        /**
         * Checks whether this specific task is done.
         */

        return completedTask;
    }

    public String getTaskDescription() {
        /**
         * Gets description for this task.
         */

        return taskDescription;
    }

}
