public class Task {
    protected String description;
    protected Boolean isDone;

    public Task(String d) {
        this.isDone = false;
        this.description = d;
    }

    public String getStatusIcon() {
        return this.isDone ? "X" : " ";
    }

    @Override
    public String toString() {
        return this.description;
    }
}
