package duke.taskmanager.task;

public abstract class Task {
    private final String taskName;
    private boolean status; // True = Done, False = Not Done
    Task(String taskName) {
        this.taskName = taskName;
        this.status = false;
    }

    Task(String taskName, boolean status) {
        this.taskName = taskName;
        this.status = status;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public abstract boolean isEmpty();
    public abstract String getFormattedString();

    @Override
    public String toString() {
        return "[" + (status ? "X":" ") + "] " + this.taskName;
    }
}