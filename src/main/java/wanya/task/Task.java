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
            String taskAlreadyCompleteMsg = "Wanya is confused errrrr... You have already marked this task as done!";
            return taskAlreadyCompleteMsg;
        }
        this.hasCompleted = true;
        String taskCompletedMsg = "Hehe well done! One task down, one step closer to play time!\n"
                + "This task has been completed:\n"
                + this;
        return taskCompletedMsg;
    }

    /**
     * Marks the task as uncompleted.
     *
     * @return String message for setting a task to be incomplete.
     */
    public String setIncomplete() {
        if (!hasCompleted) {
            String taskAlreadyIncompleteMsg = "Wanya is confused errrrr... This task is not done yet!";
            return taskAlreadyIncompleteMsg;
        }
        hasCompleted = false;
        String taskIncompleteMsg = "Oh nooo!!! Gotta buck up and finish up your tasks before you can play games.\n"
                + "This task has not been completed:\n"
                + this;
        return taskIncompleteMsg;
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
