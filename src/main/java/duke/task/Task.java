package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(boolean done, String description) {
        this.isDone = done;
        this.description = description;
    }

    private String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markTask(boolean done) {
        this.isDone = done;
    }

    public abstract String getTask();

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}
