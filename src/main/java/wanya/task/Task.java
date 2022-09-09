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

    public String getTaskName() {
        return taskName;
    }

    public boolean hasCompleted() {
        return hasCompleted;
    }

    /**
     * Marks the task as completed.
     *
     * @return String message for setting a task to be complete.
     */
    public String setComplete() {
        if (hasCompleted) {
            return("Wanya is confused errrrr... You have already marked this task as done!");
        }
        this.hasCompleted = true;
        return("Hehe well done! One task down, one step closer to play time!\n"
                + "This task has been completed:\n"
                + this);
    }

    /**
     * Marks the task as uncompleted.
     *
     * @return String message for setting a task to be incomplete.
     */
    public String setIncomplete() {
        if (!hasCompleted) {
            return("Wanya is confused errrrr... This task is not done yet!");
        }
        hasCompleted = false;
        return("Oh nooo!!! Gotta buck up and finish up your tasks before you can play games.\n"
                + "This task has not been completed:\n"
                + this);
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
