package duke;

abstract class Task {
    private final String name;
    private boolean isDone = false;

    Task(String name) {
        this.name = name;
    }

    Task(String name, Boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    String getName() {
        return name;
    }

    void setStatus(boolean b) {
        isDone = b;
    }

    boolean getStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return name;
    }
}
