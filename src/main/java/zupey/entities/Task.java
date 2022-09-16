package zupey.entities;

/** Task entity */
public abstract class Task {

    private final String name;
    private boolean isDone = false;

    public Task(String name) {
        this.name = name;
    }

    /**
     * Constructs a Task entity.
     *
     * @param name
     * @param done
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.isDone = done;
    }

    public void setDone(boolean done) {
        this.isDone = done;
    }

    public String toString() {
        return String.format("[%s] %s", this.isDone ? "x" : " ", this.name);
    }
}
