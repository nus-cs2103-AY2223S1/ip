package tasks;

public class Task {

    private final String name;
    private boolean marked;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        marked = true;
    }

    public void unmark() {
        marked = false;
    }

    @Override
    public String toString() {
        return "[" + (marked ? "X" : " ") + "] " + name;
    }

}
