public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean markAsDone() {
        isDone = true;
        return true;
    }

    public boolean markAsNotDone() {
        isDone = false;
        return false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description + "]";
    }
}
