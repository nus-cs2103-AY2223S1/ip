public abstract class Task {
    private String task;
    private boolean isDone;

    public Task() {
        this.task = "";
        isDone = false;
    }

    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    public Task(String task, boolean isDone) {
        this.task = task;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone
                ? "[X]"
                : "[ ]";
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + task;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String getTask() {
        return task;
    }

    public abstract String toSaveFileString();
}
