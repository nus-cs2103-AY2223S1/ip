public class Task {
    public String details;
    public boolean isDone;

    public Task(String details) {
        this.details = details;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    public String getTaskIcon() {
        return " ";
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return "[" + getTaskIcon() + "][" + getStatusIcon() + "] " + getDetails();
    }
}
