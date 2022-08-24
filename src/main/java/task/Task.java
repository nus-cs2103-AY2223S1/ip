package task;

public abstract class Task {

    protected final String name;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String toString() {
        return String.format("[%s] %s", this.getStatus(), this.getName());
    }

    public String toFileString() {
        return String.format("%s||%s||%s", this.getType(), this.getBooleanStatus(), this.getName());
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public abstract String getTime();
    public abstract String getType();

    public String getStatus() {
        return this.isDone() ? "X" : " ";
    }

    public String getBooleanStatus() {
        return this.isDone() ? "true" : "false";
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }
}