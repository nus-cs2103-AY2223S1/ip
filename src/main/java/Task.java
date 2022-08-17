import java.util.Arrays;

public abstract class Task {

    protected final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.getName());
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    abstract String getType();

    public String getStatus() {
        return this.isDone ? "X" : " ";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}