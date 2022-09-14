public abstract class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public abstract String getTaskType();

    public String stringifyTask() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    public boolean getStatus() {
        return this.isDone;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
