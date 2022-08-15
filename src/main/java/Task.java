

public abstract class Task {
    private final String description;
    private boolean isDone;

    /**
     * Creates a new Task object, which encapsulates a task to be completed.
     * It is set to not done by default.
     *
     * @param description the description of the event
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns an icon to indicate if the task is done.
     * @return  X if the task is done, and a space otherwise
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns isDone status of the task.
     * @return  status of task
     */
     public boolean getStatus() {
        return isDone;
    }

    public void setDone() {
        this.isDone = true;
    }

    public void setNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the string representation of the task, with the status in square brackets before the description.
     * @return string representation of the task
     */
    @Override
    public String toString(){
        return "[" + getStatusIcon() + "]" +  " " + this.description;
    }
}