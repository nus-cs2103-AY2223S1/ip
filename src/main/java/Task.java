public class Task {
    // Class Fields
    protected String taskName;
    protected boolean isDone;

    // Constructor
    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    // Methods
    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }
    public String getStatusIcon() {
        return this.isDone ? "✔" : " ";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + taskName;
    }
}