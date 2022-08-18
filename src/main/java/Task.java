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

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + details;
    }
}
