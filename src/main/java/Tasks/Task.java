package Tasks;

public class Task {
    private boolean marked = false;
    private String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void setMarked() {
        this.marked = true;
    }

    public void setUnmarked() {
        this.marked = false;
    }

    public String getMarkedStatus() {
        return this.marked ? "[X]" : "[ ]";
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getDate() {
        return "";
    }

    public String toString() {
        return  String.format("%s %s", getMarkedStatus(), this.taskName);
    }
}
