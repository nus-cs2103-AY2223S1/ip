/*
This class encapsulates the idea of a generic task
 */
public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String toString() {
        return this.description;
    }

    public void markasDone() {
        this.isDone = true;
    }

    public void markasNotDone() {
        this.isDone = false;
    }
}
