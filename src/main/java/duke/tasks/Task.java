package duke.tasks;

public abstract class Task {

    private final String name;
    private boolean isMarked;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        isMarked = true;
    }

    public void unmark() {
        isMarked = false;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + name;
    }

    public abstract String toDataString();

}
