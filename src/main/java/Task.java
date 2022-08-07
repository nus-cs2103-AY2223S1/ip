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

    public void setDone() {
        isDone = true;
    }

    public void removeDone() {
        isDone = false;
    }

    public String fullStatusIcon() {
        return "[" + getStatusIcon() + "]";
    }

    @Override
    public String toString() {
        return fullStatusIcon() + " " + description;
    }
}