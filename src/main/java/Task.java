public class Task {
    String name;
    boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        String icon = this.isDone ? "[X] " : "[ ] ";
        return icon + this.name;
    }
}
