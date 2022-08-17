public class Task {
    private String taskName;
    private boolean isDone;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isDone = false;
    }

    public void setTaskStatus(boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public Boolean getTaskStatus() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return (this.getTaskStatus() ? "[X]" : "[ ]") + " " + this.getTaskName();
    }
}
