package task;

import exceptions.InvalidIndex;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Represents a Task, with a given name and status (completed or not completed).
 */
public class Task {

    private String name;
    private boolean done;


    public Task(String s) {
        this.name = s;
        this.done = false;
    }

    /**
     * Marks a task as done.
     *
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks a task as undone.
     *
     */
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

    public void write(FileWriter writer) throws IOException {
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
