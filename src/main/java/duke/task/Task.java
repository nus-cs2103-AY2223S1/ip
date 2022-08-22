package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {
    private String name;
    private boolean isComplete;
    private char type;

    public Task(String name, char type, boolean isComplete) {
        this.name = name;
        this.type = type;
        this.isComplete = isComplete;
    }

    public Task(String name, char type) {
        this(name, type, false);
    }

    public void markComplete() {
        this.isComplete = true;
    }

    public void markIncomplete() {
        this.isComplete= false;
    }

    @Override
    public String toString() {
        return String.format("[%c][%s] %s", type, isComplete ? "X" : " ", name);
    }
}
