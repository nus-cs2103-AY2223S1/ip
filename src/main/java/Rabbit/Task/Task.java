package rabbit.task;

/**
 * A class of tasks noted by Rabbit.
 */
public abstract class Task {
    // the specification of the task.
    private String content;
    // whether the task is marked as done.
    private boolean isDone;

    /** a constructor of Task
     *
     * @param content the content of the task
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    /** a constructor of Task.
     *
     * @param content the content of the task.
     * @param isDone whether the task is done.
     */
    public Task(String content, boolean isDone) {
        this.content = content;
        this.isDone = isDone;
    }

    /**
     * returns the content of the task
     *
     * @return the content of the task
     */
    public String getContent() {
        return this.content;
    }

    /**
     * returns true if the task is marked as done,
     * false otherwise
     *
     * @return true if the task is marked as done,
     * false otherwise
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.isDone ? "[X] " + this.content : "[ ] " + this.content;
    }
}
