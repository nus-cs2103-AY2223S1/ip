public class Task {
    protected String description;
    protected boolean isDone;

    public static int totalDone = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    protected String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone() {
        if (!this.isDone) {
            Task.totalDone++;
        }
        this.isDone = true;
    }

    public void setUnDone() {
        if (this.isDone) {
            Task.totalDone--;
        }
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
