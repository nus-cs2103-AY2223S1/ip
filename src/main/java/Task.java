public class Task {
    enum taskType {
        T, D, E
    }
    private boolean marked = false;
    private String taskName;
    private taskType type;
    private String date = "";

    public Task(String taskName, taskType type) {
        this.taskName = taskName;
        this.type = type;
    }

    public Task(String taskName, taskType type, String date) {
        this.taskName = taskName;
        this.type = type;
        this.date = type == taskType.D ? "(by: " + date + ")" : "(at: " + date + ")";
    }

    public void setMarked() {
        this.marked = true;
    }

    public void setUnmarked() {
        this.marked = false;
    }

    public String toString() {
        String mark = marked ? "[X] " : "[ ] ";
        return "[" + this.type + "]" +  mark + this.taskName + date;
    }
}
