package tasks;

public abstract class Task {
    protected final String description;
    protected boolean isMarked;

    public Task(String name) {
        this.description = name;
        this.isMarked = false;
    }

    public Task(boolean isMarked, String description) {
        this.isMarked = isMarked;
        this.description = description;
    }

    public void mark(boolean isCompleted) {
        this.isMarked = isCompleted;
    }

    public abstract String dbRepresentation();

    private String getStatusIcon() {
        return this.isMarked ? "X" : " ";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
