public class Task {
    private final String detail;
    private final boolean isDone;

    Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    private Task(String detail, boolean isDone) {
        this.detail = detail;
        this.isDone = isDone;
    }
    Task markDone() {
        return new Task(this.detail, true);
    }

    Task unmarkDone() {
        return new Task(this.detail, false);
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.detail;
    }
}
