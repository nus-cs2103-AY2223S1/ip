package duke.taskmanager.task;

public abstract class Task {
    private final String taskName;
    private boolean isCompleted;
    Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isCompleted() {
        return this.isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public abstract boolean isEmpty();
    public abstract String getFormattedString();

    @Override
    public String toString() {
        return "[" + (isCompleted ? "X":" ") + "] " + this.taskName;
    }
}