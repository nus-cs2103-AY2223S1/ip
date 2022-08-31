public class Task {
    protected String taskName;
    protected boolean isDone;
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return this.taskName;
    }

    public String toStore() {return (this.isDone ? 1 : 0) + "|" + this.taskName;}

}
