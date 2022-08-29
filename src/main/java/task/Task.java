package task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Return description of Task.
     * @return String containing description of task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the status of each task's isDone.
     * @param isDone Status of isDone to be applied to Task.
     */
    public void setStatus(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Return isDone status of Task.
     * @return Boolean containing isDone status of Task.
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Return status icon of Task used in chat Ui.
     * @return String containing status icon of Task.
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Save format of Task to be saved into data file upon save command.
     * @return String containing the save format of all tasks in taskList.
     */
    public abstract String getSaveFormat();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
