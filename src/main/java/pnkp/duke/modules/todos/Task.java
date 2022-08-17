package pnkp.duke.modules.todos;

import static java.lang.String.format;

public abstract class Task {
    private String name;
    private boolean done;

    public Task(String name) {
        this(name, false);
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return format("[%s] %s", this.done ? "X" : " ", this.name);
    }
}
