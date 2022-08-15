public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "[" + (this.isDone ? "x" : " ") + "] " + this.description;
    }

    public boolean changeStatus(Duke.TaskStatus status) {
        if (status == Duke.TaskStatus.MARK) {
            return this.markDone();
        }
        return this.markUndone();
    }

    private boolean markDone() {
        boolean temp = this.isDone;
        this.isDone = true;
        return !temp;
    }

    private boolean markUndone() {
        boolean temp = this.isDone;
        this.isDone = false;
        return temp;
    }
}
