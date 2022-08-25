package duke.task;

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

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getStatusInt() { return (isDone ? "1" : "0"); }

    public void setStatus(boolean status) {
        isDone = status;
    }

    public abstract String getType();

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
