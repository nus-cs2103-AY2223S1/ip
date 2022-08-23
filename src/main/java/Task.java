/*
This class encapsulates the idea of a generic task
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description, boolean status) {
        this.description = description;
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDescription() { return description; }

    public String toString() {
        return description;
    }

    public boolean getStatus() { return this.isDone; };

    public void markasDone() {
        this.isDone = true;
    }

    public void markasNotDone() {
        this.isDone = false;
    }
}
