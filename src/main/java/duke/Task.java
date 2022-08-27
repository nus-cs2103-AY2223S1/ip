package duke;

public abstract class Task {
    private final int id;
    private final String name;
    private boolean isDone = false;

    Task(int id, String name) {
        this.id = id;
        this.name = name;
    }

    Task(int id, String name, Boolean isDone) {
        this(id, name);
        this.isDone = isDone;
    }

    void setDone() {
        this.isDone = true;
    }

    void setNotDone() {
        this.isDone = false;
    }

    int getId() {
        return id;
    }

    boolean getStatus() {
        return isDone;
    }

    @Override
    public String toString() {
        return name;
    }
}
