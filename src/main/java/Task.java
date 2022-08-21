import java.time.format.DateTimeFormatter;
/**
 * Represents a task.
 * Contains description of task and task completion status.
 */
public class Task {
    protected final static DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy");
    private String description;
    private boolean isDone;

    Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }

    void unmarkAsDone() {
        this.isDone = false;
    }

    String getStorageFormat() {
        return this.getStatusBit() + " | " + this.description;
    }

    int getStatusBit() {
        return isDone ? 1 : 0;
    }

    String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.description;
    }
}
