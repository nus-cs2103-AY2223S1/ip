package Tasks;

/**
 * Parent class for all types of items in task list
 */
public class Task {
    enum Priority {
        H, M, L
    }
    private boolean marked = false;
    private Priority priority = Priority.L;
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

    public void setPriority(String s) {
        switch (s) {
        case "H":
            this.priority = Priority.H;
            break;
        case "M":
            this.priority = Priority.M;
            break;
        case "L":
            this.priority = Priority.L;
            break;
        }
    }

    public String getPriorityStatus() {
        return String.format("[%s]", priority.toString());
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
        return  String.format("%s%s %s", getPriorityStatus(), getMarkedStatus(), this.taskName);
    }
}
