package duke.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert(!description.isEmpty());
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, Boolean isDone) {
        assert(!description.isEmpty());
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void toggleStatus() {
        if (this.isDone) {
            this.isDone = false;
        } else {
            this.isDone = true;
        }
    }

    public abstract String formatTaskString();


}
