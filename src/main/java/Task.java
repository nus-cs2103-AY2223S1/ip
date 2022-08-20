/**
 * Project done by Hong Jin.
 */

import java.util.*;

/**
 * Class Task to store Task.
 */
public class Task {
    private String task;
    private String done;

    public Task(String task) {
        this.task = task;
        this.done = "[ ]";
    }

    public void mark() {
        this.done = "[X]";
    }

    public void unmark() {
        this.done = "[ ]";
    }

    public String getMark() {return this.done;}

    @Override
    public String toString() {
        return String.format("%s %s", getMark(), this.task);
    }
}
