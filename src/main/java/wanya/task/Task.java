package wanya.task;

/**
 * Represents a task to be completed.
 */
public abstract class Task {
    private boolean hasCompleted = false;
    private String taskName;

    /**
     * Creates a task when given a task name.
     *
     * @param taskName name of the task.
     */
    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Creates a task when given a task name and completeness.
     *
     * @param taskName name of the task.
     * @param hasCompleted whether the task has been completed.
     */
    public Task(String taskName, boolean hasCompleted) {
        this.taskName = taskName;
        this.hasCompleted = hasCompleted;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean hasCompleted() {
        return hasCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void setComplete() {
        if (hasCompleted) {
            System.out.println("Wanya is confused errrrr... You have already marked this task as done!\n");
        }
        this.hasCompleted = true;
        System.out.println("Hehe well done! One task down, one step closer to play time!\n" +
                "This task has been completed:");
        System.out.println(this + "\n");
    }

    /**
     * Marks the task as uncompleted.
     */
    public void setIncomplete() {
        if (!hasCompleted) {
            System.out.println("Wanya is confused errrrr... This task is not done yet!\n");
        }
        hasCompleted = false;
        System.out.println("Oh nooo!!! Gotta buck up and finish up your tasks before you can play games.\n" +
                "This task has not been completed:");
        System.out.println(this + "\n");
    }

    /**
     * Encode a task object to a String representation for storage.
     *
     * @return String representation of the task in storage.
     */
    public String toStorageString() {
        String status = hasCompleted ? "1" : "0";
        return status + "|" + taskName;
    }

    /**
     * Returns the String representation of the task.
     *
     * @return String representation of the task.
     */
    @Override
    public String toString() {
        String checkbox;
        if (hasCompleted()) {
            checkbox = "[X] ";
        } else {
            checkbox = "[ ] ";
        }
        return checkbox + taskName;
    }

}
