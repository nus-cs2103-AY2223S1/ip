package duke;

import java.time.LocalDateTime;

/**
 * Task class.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public LocalDateTime getDateTime() {
        return null;
    }

    protected enum Type { DEADLINE, TODO, EVENT }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * String format for text file.
     *
     * @return string to be written into the text file.
     */
    public String toStringFileFormat() {
        return (isDone ? 1 : 0) + " | " + this.description;
    }

    /**
     * String representation of Task.
     *
     * @return string representation of Task.
     */

    public boolean containsWord(String str) {
        return description.contains(str);
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
