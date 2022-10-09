package duke.tasks;

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

    /**
     *  Marks task done with X
     * @return status of task with X or " "
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Updates boolean isDone depending on the status of the task
     */
    public void mark() {
        this.isDone = true;
    }

    /**
     * Updates boolean isDone depending on the status of the task
     */
    public void unmark() {
        this.isDone = false;
    }

    /** Returns formatted String to write back to our data
     *
     * @return String to save to our stored data
     */
    public String writeToFile() {
        int status = isDone ? 1 : 0;
        return status + "|" + this.name;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.name;
    }
}
