package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.util.ParsedData;

public abstract class Task implements Comparable<Task> {
    protected final String description;
    protected boolean completed;
    protected Optional<LocalDateTime> dateTime;

    Task(String description, Optional<LocalDateTime> dateTime) {
        this.description = description;
        this.completed = false;
        this.dateTime = dateTime;
    }

    Task(String description) {
        this(description, Optional.empty());
    }

    public void mark() {
        completed = true;
    }

    public void unmark() {
        completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    Optional<LocalDateTime> getTimestamp() {
        return dateTime;
    }

    @Override
    public int compareTo(Task o) {
        if (!o.dateTime.isPresent()) {
            return -1;
        } else if (!dateTime.isPresent()) {
            return 1;
        }

        return dateTime.get().compareTo(o.dateTime.get());
    }

    public int compareTo(LocalDateTime o) {
        if (!dateTime.isPresent()) {
            return 1;
        }

        return dateTime.get().compareTo(o);
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", (completed) ? "X" : " ", description);
    }

    abstract public ParsedData convertToParseData();
}
