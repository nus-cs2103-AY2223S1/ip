/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.task;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Class Task to store Task.
 */
public abstract class Task {
    protected String task;
    protected String done;

    /**
     * public constructor for Task.
     * @param task
     */
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

    /**
     * class method to return String representation of Task.
     * @return String.
     */
    @Override
    public String toString() {
        return String.format("%s %s", this.done, this.task);
    }
}
