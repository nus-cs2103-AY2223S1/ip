package task;

/**
 *  A class which encapsulates a task that can be added to the task list.
 *  @author  Chen Guanzhou
 *  @version v2
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * A method to delay the deadline of the task by 1 day.
     * @return The snoozed task as Duke's response.
     */
    public abstract String snooze();
    /**
     * A setter to change the task to be done.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * A setter to change the task to be undone.
     */
    public void setUndone(){
        this.isDone = false;
    }

    /**
     * String representation of the current task description.
     * @return The task description and whether the task is done as a string.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Gives the done status of the current task.
     * @return The current done status of a task.
     */
    public boolean getDoneStatus() {
        return this.isDone;
    }

    /**
     * String representation of the task status with description.
     * @return Task status icon and description.
     */
    @Override
    public String toString(){
        return this.isDone ? "[X] " + this.description : "[ ] " + this.description;
    }
}
