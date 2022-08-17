package duke;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    public void toggleStatus() {
        this.isDone = !isDone;
    }

    public boolean isDone() {
        return this.isDone;
    }

}
