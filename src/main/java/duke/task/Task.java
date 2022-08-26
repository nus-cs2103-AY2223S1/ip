package duke.task;

import java.time.LocalDateTime;

/**
 * Represents Tasks save by Duke.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    protected Task(String name) {
        setName(name);
        setIsDone(false);
    }

    public static Todo todo(String msg) {
        return new Todo(msg);
    }

    public static Event event(String msg, LocalDateTime time) {
        return new Event(msg, time);
    }

    public static Deadline deadline(String msg, LocalDateTime time) {
        return new Deadline(msg, time);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    private void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public abstract String toFormattedString();

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + this.getName();
    }

}
