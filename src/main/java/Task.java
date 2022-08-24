public class Task {
    protected String description;
    protected boolean isDone;
    protected String type = "";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getType() {
        return this.type;
    }

    public void taskDone() {
        this.isDone = true;
    }

    public void taskUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
