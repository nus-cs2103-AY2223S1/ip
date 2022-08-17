public class Task {
    private boolean marked;
    private String name;

    public Task(String name) {
        this.name = name;
        this.marked = false;
    }

    public boolean isMarked() {
        return this.marked;
    }

    public String getStatusIcon() {
        return (marked ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + name;
    }
}
