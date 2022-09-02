package duke.tasks;

public abstract class Task {

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

    public boolean isMarked() {
        return marked;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + (marked ? "X" : " ") + "] " + name;
    }

    public abstract String toDataString();

}
