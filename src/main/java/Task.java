public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /*
    * Get the status of the task.
    *
    * @return String.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /*
    * Set the task to Done.
    */
    public void SetDone() {
        this.isDone = true;
    }

    /*
    * Set the task to not done yet.
     */
    public void SetNotDone() {
        this.isDone = false;
    }
}
