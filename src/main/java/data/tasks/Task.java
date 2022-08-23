package data.tasks;

import java.io.Serializable;

public abstract class Task implements Serializable {

    protected final String title;
    protected boolean marked;

    Task(String title) {
        this.title = title;
        this.marked = false;
    }

    public void mark() {
        this.marked = true;
    }

    public void unmark() {
        this.marked = false;
    }

    @Override
    public String toString() {
        return String.format("[%c] %s", this.marked ? 'X' : ' ', this.title);
    }
}
