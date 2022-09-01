package duke;

import java.time.LocalDateTime;

abstract public class Task {
    private String name;
    private boolean isDone;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    void Done() {
        this.isDone = true;
    }

    void unDone() {
        this.isDone = false;
    }

    boolean getDone() {
        return this.isDone;
    }

    String getName() {
        return this.name;
    }

    abstract LocalDateTime getDateTime();
    abstract boolean isToDo();

    @Override
    public String toString() {
        String out = "";
        if (this.isDone) {
            out += "[X]";
        } else {
            out += "[ ]";
        }
        out += " " + this.name;
        return out;
    }
}
