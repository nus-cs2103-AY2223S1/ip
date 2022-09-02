package bro.task;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor of Task class.
     * @param description Description to be given to the variable description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns 'X' if done or " " otherwise.
     * @return String "X" if the task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Gets the task type of the given task.
     * @return A string with the task type.
     */
    public String getTaskType(){
        return "bro.task.Task";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}

