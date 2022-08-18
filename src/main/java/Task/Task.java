package Task;

public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String task) {
        this.description = task;
    }

    protected void mark() {
        this.isDone = true;
    }

    protected void unmark() {
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (this.isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
