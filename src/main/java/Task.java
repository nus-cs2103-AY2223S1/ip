public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor to create a new task.
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * To get the task status
     * @return A string that describes the task status. X indicates that the task is completed.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * To mark a task as completed.
     * @return A string that notifies the user if the task has been marked as done.
     */
    public String markAsDone() {
        this.isDone = true;
        return String.format("\t> Nice! I have marked this task as done:\n\t \t %s",
                this);
    }

    /**
     * To mark a task as not completed.
     * @return A string that notifies the user if the task has been marked as not done.
     */
    public String markAsNotDone() {
        this.isDone = false;
        return String.format("\t> Okay! I have marked this task as not done:\n\t \t %s",
                this);
    }

    @Override
    /**
     * Returns a string representation of the task object.
     * @return A string representation of the task object.
     */
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

}
