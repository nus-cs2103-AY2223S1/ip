abstract class Task {
    private final String detail;
    private final boolean isDone;

    Task(String detail) {
        this.detail = detail;
        this.isDone = false;
    }

    Task(String detail, boolean isDone) {
        this.detail = detail;
        this.isDone = isDone;
    }
    abstract Task markDone();

    abstract Task unmarkDone();

    abstract String getId();

    String getDetail() {
        return this.detail;
    }

    boolean isDone() {
        return this.isDone;
    }

    @Override
    public boolean equals(Object otherTask) {
        if (otherTask instanceof Task) {
            return ((Task) otherTask).getDetail().equals(this.detail);
        }
        return false;
    }

    @Override
    public String toString() {
        return (this.isDone ? "[X] " : "[ ] ") + this.detail;
    }
}
