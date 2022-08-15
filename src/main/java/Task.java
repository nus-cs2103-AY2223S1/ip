/**
 * Project done by Hong Jin.
 */

import java.util.*;

/**
 * Class Task to store Task.
 */
public class Task {
    private String task;
    private boolean done;

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public String getTask() {
        return this.task;
    }

    public String getTaskList() {
        if (!this.done) {
            return "[ ] " + this.task;
        } else {
            return "[X] " + this.task;
        }
    }

    public void mark() {
        this.done = true;
    }

    public void unmark() {
        this.done = false;
    }
}
