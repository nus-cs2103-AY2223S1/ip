package duke;
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Default constructor for Task.
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatus() {
        return (isDone ? "1" : "0");
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String output = String.format("[%s] %s", this.getStatusIcon(), this.description);
        return output;
    }
}