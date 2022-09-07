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

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        if (time == null) {
            return description;
        }
        return description + " (" + DateTimeFormatter.ofPattern("MMM dd yyyy H:mm").format(time) + ")";
    }

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
