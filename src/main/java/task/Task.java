package task;

public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String task) {
        this.description = task;
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
}
