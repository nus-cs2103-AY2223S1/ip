import java.time.LocalDateTime;
import java.util.Optional;

abstract class Task implements Comparable<Task> {
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

    void mark() {
        completed = true;
    }

    void unmark() {
        completed = false;
    }

    boolean isCompleted() {
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

    @Override
    public String toString() {
        return String.format("[%s] %s", (completed) ? "X" : " ", description);
    }

    abstract public ParsedData convertToParseData();
}
