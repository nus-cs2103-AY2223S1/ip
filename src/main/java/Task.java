public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone? "[X]" : "[ ]");
    }

    public void toMark(boolean x) {
        this.isDone = x;
    }
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
