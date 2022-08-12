public class Task {
    private final String title;
    private final boolean status; 

    Task(String title, boolean status) {
        this.title = title;
        this.status = status;
    }

    Task setStatus(boolean status) {
        return new Task(this.title, status);
    }

    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.title;
    }
}
