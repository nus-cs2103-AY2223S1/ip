package duke.task;

public class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getOutput() {
        return String.format("O | %d | %s", isDone ? 1 : 0, description);
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }
}
