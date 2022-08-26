package Duke;

import java.time.LocalDateTime;

abstract public class Task {
    private String name;
    private boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    void Done() {
        this.done = true;
    }

    void unDone() {
        this.done = false;
    }

    boolean getDone() {
        return this.done;
    }

    String getName() {
        return this.name;
    }

    abstract LocalDateTime getDateTime();
    abstract boolean isToDo();

    @Override
    public String toString() {
        String out = "";
        if (this.done) {
            out += "[X]";
        } else {
            out += "[ ]";
        }
        out += " " + this.name;
        return out;
    }
}
