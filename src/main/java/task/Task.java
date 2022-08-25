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

    public void markDone() {
        this.done = true;
    }

    public void markUndone() {
        this.done = false;
    }

    public String getName() {
        return this.name;
    }
    public int getDone() {
        if (this.done) {
            return 1;
        } else {
            return 0;
        }
    }

    public void write(FileWriter fw) throws IOException {
    };

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
