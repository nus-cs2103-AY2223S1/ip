public class Task {
    private boolean done;
    private String description;

    Task(String description) {
        this.done = false;
        this.description = description;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }

    public String toString() {
        return String.format("[T][%s] %s", this.done ? 'X' : ' ', this.description);
    }
}
