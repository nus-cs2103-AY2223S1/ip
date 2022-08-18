public class Task {

    private String description;
    private boolean isDone = false;

    public Task (String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    private String getStatusIcon() {
        return (this.isDone ? "X" : " "); // mark done task with X
    }

    public boolean taskDone() {
        return this.isDone;
    }

}

