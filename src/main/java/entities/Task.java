package entities;

public abstract class Task {

    private final String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String toString() {
        return String.format("[%s] %s", this.done ? "x" : " ", this.name);
    }
}