public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getIcon() {
        return isDone ? "X" : " ";
    }

    public String toString() {
        return String.format("[%s] %s", this.getIcon(), this.description);
    }
}
