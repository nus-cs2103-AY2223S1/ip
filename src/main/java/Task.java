public class Task {
    protected String content;
    protected boolean isDone;

    Task (String c) {
        this.content = c;
        this.isDone = false;
    }

    Task (String c, boolean isDone) {
        this.content = c;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + this.content;
    }
}
