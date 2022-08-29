package duke.tasks;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }

    public void setTaskStatus(boolean status) {
        this.isDone = status;
    }

    public String taskMemo() {
        int status = this.isDone ? 1 : 0;
        return String.format(" | %d | %s", status, this.description);
    }
}
