/**
 * A class of tasks noted by Rabbit.
 */
public abstract class Task {
    // the specification of the task.
    private String content;
    // whether the task is marked as done.
    private boolean done;

    /** a constructor of Task
     *
     * @param content the content of the task
     */
    public Task(String content) {
        this.content = content;
        this.done = false;
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
        return this.done;
    }

    /**
     * marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }

    @Override
    public String toString() {
        return this.done ? "[X] " + this.content : "[ ] " + this.content;
    }
}
