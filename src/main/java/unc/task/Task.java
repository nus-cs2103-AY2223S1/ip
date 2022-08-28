package unc.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for new Tasks.
     *
     * @param description Input description following keyword.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Constructor for saved Tasks.
     *
     * @param description Input description following keyword.
     * @param isDone Whether the task was saved as done.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns an icon representing whether the task is done.
     *
     * @return "X" for done, " " for not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Sets the status to done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Sets the status to not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString(){
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns task in the format to be stored in file.
     *
     * @return Reformatted task.
     */
    public abstract String toStorageString();

}
