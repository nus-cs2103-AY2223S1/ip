public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, int status) {
        this.description = description;
        this.isDone = status == 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    public String parseToSaveData() {
        int isDoneNum = isDone ? 1 : 0;
        return isDoneNum + "|" + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " +  description;
    }
}
