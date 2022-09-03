package task;

public abstract class Task {

    private String description;
    private boolean isDone;


    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

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
