public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }
    public void flip() {
        this.isDone = !isDone;
    }

    public void markDone() {
        if (!this.isDone) {
            this.flip();
        }
    }

    public void markUndone() {
        if (this.isDone) {
            this.flip();
        }
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
    }
}