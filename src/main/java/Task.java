public class Task {
    public String details;
    public boolean done;

    public Task(String details) {
        this.details = details;
        this.done = false;
    }

    public String getStatusIcon() {
        return done ? "X" : " ";
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
