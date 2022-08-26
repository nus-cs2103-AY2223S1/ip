package duke;

import java.time.LocalDateTime;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public void mark() {
        this.isDone = true;
    }

    public void unmark() {
        this.isDone = false;
    }

    public LocalDateTime getDateTime() {
        return LocalDateTime.of(0, 1, 1, 0, 0);
    }

    public String getWriteString() {
        String marked = this.isDone() ? "1" : "0";
        return String.format("%s | %s", marked, this.description);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), this.description);
    }
}