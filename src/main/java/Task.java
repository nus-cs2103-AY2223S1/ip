/**
 * A class of tasks noted by Rabbit.
 */
public class Task {
    // the specification of the task.
    private String content;
    // whether the task is marked as done.
    private boolean done;

    /** a constructor of the Task
     *
     * @param content the content of the task
     */
    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    /** a constructor of the Task
     *
     * @param content the content of the task
     * @boolean done whether the task is done
     */
    public Task(String content, boolean done) {
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
