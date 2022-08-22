package task;

public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String task) {
        description = task;
    }

    public Task(String task, boolean isDone) {
        this(task);
        this.isDone = isDone;
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    private String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + description;
    }

    public String encode() {
        return ",,," + (isDone ? 1 : 0) + ",,," + description;
    }
}
