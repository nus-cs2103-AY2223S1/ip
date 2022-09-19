package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    enum Tag {
        Todo,
        Event,
        Deadline
    }

    protected LocalDateTime time;
    protected String description;
    protected Tag tag;
    protected boolean isDone;

    public Task(String description, Tag tag, LocalDateTime time) {
        this.description = description;
        this.tag = tag;
        this.time = time;
        this.isDone = false;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    public String getName() {
        return description;
    }

    /**
     * Returns String corresponding to whether task is done or not.
     * Returns "X" if task is done, and " " if not.
     * @return String representing whether task is done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns String corresponding to task description.
     * If task has non-null time, time will also be printed in parentheses.
     * @return Description of the task.
     */
    public String getDescription() {
        if (time == null) {
            return description;
        }
        return description + " (" + DateTimeFormatter.ofPattern("MMM dd yyyy H:mm").format(time) + ")";
    }

    /**
     * Returns String corresponding to type of task.
     * @return Type of the task.
     */
    public String getTag() {
        switch (tag) {
        case Todo:
            return "T";
        case Event:
            return "E";
        case Deadline:
            return "D";
        default:
            return " ";
        }
    }

    public LocalDateTime getTime() {
        if (time == null) {
            return LocalDateTime.MAX;
        }
        return time;
    }

    public String toString() {
        return "[" + getTag() + "][" + getStatusIcon() + "] " + getDescription();
    }
}
