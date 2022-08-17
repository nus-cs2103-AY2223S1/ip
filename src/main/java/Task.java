public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markCompleted() {
        this.isDone = true;
    }

    public void markUncompleted() {
        this.isDone = false;
    }
}
