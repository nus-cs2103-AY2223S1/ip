public abstract class Task {
    private final String description;
    private boolean isCompleted;

    public Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public void markAsUndone() {
        this.isCompleted = false;
    }

    public String getStatus() {
        return isCompleted ? "X" : " ";
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String getType();
}
