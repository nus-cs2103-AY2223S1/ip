public class Task {
    private boolean isDone = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setMarked() {
        this.isDone = true;
    }

    public void setUnmarked() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return this.isDone ? "[X]" : "[ ]";
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getDateline() {
        return "";
    }

    public String toString() {
        return  String.format("%s %s", getStatusIcon(), this.taskName);
    }
}
