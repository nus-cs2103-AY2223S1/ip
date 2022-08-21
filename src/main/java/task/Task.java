package task;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return the String representing whether the task is done or not.
     *
     * @return the String representing whether the task is done or not.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the boolean representing whether the isDone value can
     * be changed to the new value.
     *
     * @param newIsDone the new isDone value to be checked.
     * @return the boolean representing whether the isDone value can
     * be changed to the new value.
     */
    public boolean canChangeIsDone(boolean newIsDone) {
        return this.isDone != newIsDone;
    }

    /**
     * Changes the current isDone value to the input isDone.
     *
     * @param newIsDone the newIsDone value to be changed.
     */
    public void changeIsDone(boolean newIsDone){
        this.isDone = newIsDone;
    }

    /**
     * Return the description of the Task.
     *
     * @return the description of the Task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the current description to the input description.
     *
     * @param newDescription to be changed to.
     */
    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    /**
     * Return the isDone value representing whether the Task is done.
     *
     * @return the isDone value.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
