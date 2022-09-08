package task;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a Task, with a given name and status (completed or not completed).
 */
public abstract class Task {

    private String name;
    private boolean isDone;


    public Task(String s) {
        this.name = s;
        this.isDone = false;
    }

    public void setDone(boolean b) {
        this.isDone = b;
    }

    public String getName() {
        return this.name;
    }

    public int getDoneInt() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public abstract void write(FileWriter writer) throws IOException;


    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract boolean equals(Task task);

}
