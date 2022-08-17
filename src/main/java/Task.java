public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Task markAsDone() {
        return new Task(this.description, true);
    }

    public Task unMark() {
        return new Task(this.description, false);
    }

    @Override
    public String toString() {
        return this.description;
    }
}