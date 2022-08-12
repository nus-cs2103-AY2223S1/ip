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

    public String toString() {
        String mark = marked ? "[X] " : "[ ] ";
        return mark + this.taskName;
    }
}
