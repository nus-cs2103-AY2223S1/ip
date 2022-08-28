package bloop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        isDone = false;
    }

    protected String getTask() {
        return task;
    }

    protected boolean getStatus() {
        return isDone;
    }

    protected void mark() {
        isDone = true;
    }

    protected void unmark() {
        isDone = false;
    }

    protected abstract String getBy();

    protected abstract char getType();

    @Override
    public String toString() {
        return (isDone ? "[X]" : "[ ]") + " " + task;
    }

    protected String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a");
        return dateTime.format(outputFormatter);
    }
}
