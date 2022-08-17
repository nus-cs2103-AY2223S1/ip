public class Task {
    private final String taskName;
    private final boolean markDone;

    Task(String taskName) {
        this.taskName = taskName;
        this.markDone = false;
    }

    Task(String taskName, boolean markDone) {
        this.taskName = taskName;
        this.markDone = markDone;
    }
    public Task mark() {
        return new Task(this.taskName, true);
    }

    public Task unmark() {
        return new Task(this.taskName, false);
    }

    public boolean isMarked() {
        return this.markDone;
    }

    public String getTaskName() {
        return taskName;
    }

    @Override
    public String toString() {
        return "[" + (this.isMarked() ? "X" : " ") + "]" + this.taskName + "\n";
    }
}
