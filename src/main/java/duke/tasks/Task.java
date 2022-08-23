package duke.tasks;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public abstract String getStatus();

    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Returns description of task.
     * @return Description of task.
     */
    public String getDescription() {
        return this.description;
    }

}
