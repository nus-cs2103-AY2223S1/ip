/**
 * Project done by Hong Jin.
 */
package duke;

import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    @Override
    public String toString() {
        return String.format("%s %s", this.done, this.task);
    }
}
