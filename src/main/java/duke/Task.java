package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Parent class for todo, Event and Deadline to inherit from
 */
public class Task {
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getDescription() {
        int status = isDone ? 1 : 0;
        return status + " | " + this.name;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /** Updates the status of our task */
    void mark() {
        this.isDone = true;
    }

    /** Updates the status of our task */
    void unmark() {
        this.isDone = false;
    }

    /** Returns formatted String to write back to our data
     *
     * @return String to save to our stored data
     */
    String writeToFile() {
        int status = isDone ? 1 : 0;
        return status + "|" + this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
