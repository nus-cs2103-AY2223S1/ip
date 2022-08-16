public abstract class Task {
    private final String taskDescription;
    private boolean isDone;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        this.isDone = false;
    }

    public void isDoneSetter(boolean isDone) {
        this.isDone = isDone;
    }

    public abstract String getTypeIcon();

    public String getStatusIcon() {
        return "[" + (isDone ? "X" : " ") + "]";
    }

    @Override
    public String toString() {
        return getTypeIcon() + getStatusIcon() + " " + taskDescription;
    }
}
