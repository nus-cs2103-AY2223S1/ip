package task;

public class Task {
    private String description;
    private boolean isFinished;

    public Task(String description) {
        this.description = description;
        this.isFinished = false;
    }

    public void markAsFinished() {
        this.isFinished = true;
    }

    public void markAsNotFinished() {
        this.isFinished = false;
    }

    public String getStatusIcon() {
        return this.isFinished ? "X" : " ";
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
