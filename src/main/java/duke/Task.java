package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Parent class for common attributes between tasks.
 */
public abstract class Task {
    protected String taskName;
    protected boolean done;
    public abstract String taskToFileString();

    /**
     * Constructor for a task object.
     * @param taskName name of task.
     * @param isDone task marked done or not.
     */
    public Task(String taskName, boolean isDone) {
        this.taskName = taskName;
        this.done = isDone;
    }

    /**
     * Converts LocalDateTime into a string.
     * @param dt LocalDateTime Object.
     * @return string of format (dd/mm/yyyy hhmm).
     */
    public static String dateTimeToString(LocalDateTime dt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return dt.format(formatter);
    }

    /**
     * Marks a task object as done.
     */
    public void mark() {
        this.done = true;
    }

    /**
     * Marks a task object as not done.
     */
    public void unMark() {
        this.done = false;
    }


}
