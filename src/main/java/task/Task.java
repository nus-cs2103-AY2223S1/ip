package task;

import java.io.FileWriter;
import java.io.IOException;

public class Task {

    private String name;
    private boolean done;


    public Task(String s) {
        this.name = s;
        this.done = false;
    }

    public void setDone(boolean b) {
        this.done = b;
    }

    public String getName() {
        return this.name;
    }

    public int getDoneInt() {
        if (this.done) {
            return 1;
        } else {
            return 0;
        }
    }

    public void write(FileWriter writer) throws IOException {
    }

    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public boolean isDone() {
        return done;
    }
}
