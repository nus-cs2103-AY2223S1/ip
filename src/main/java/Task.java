public class Task {
    protected String description;
    protected boolean isDone;
    protected static int total = 0;

    public Task() {
        this.description = "";
        this.isDone = false;
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.total += 1;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getTotal() {
        return this.total;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String result = "[" + getStatusIcon() + "] " + this.description;
        return result;
    }
}
