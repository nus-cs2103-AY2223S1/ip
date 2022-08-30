package duke;

/**
 * Represents task given by the users.
 */
class Task {
    private final String taskDescription;
    private boolean isDone;

    /**
     * Create task with specific description.
     * Mark as undone.
     * @param description task description given by the users.
     */
    Task(String description) {
        this.taskDescription = description;
        this.isDone = false;
    }

    /**
     * Mark the task as done.
     */
    void markAsDone() {
        this.isDone = true;
    }


    /**
     * Mark the task as undone.
     */
    void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Get the status of the task, whether as done or undone.
     * @return string "X" if done, string " " if undone.
     */
    String getStatus() {
        if (this.isDone == true) {
            return "X";
        } else {
            return " ";
        }
    }

    String getTaskDescription() {
        return this.taskDescription;
    }


    /**
     * Print out the task.
     * @return string
     */
    @Override
    public String toString() {
        return "[" + this.getStatus() + "] " + this.getTaskDescription();
    }

    /**
     * Write the task for the file.
     * @return string
     */
    public String write() {
        return ":" + this.getStatus() + ":" + this.getTaskDescription();
    }

}