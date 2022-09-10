package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATE_TIME_DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");

    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done duke.task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void maskUndone() {
        this.isDone = false;
    }

    public String encode() {
        String encodedStatus = isDone ? "1" : "0";
        return this.taskType.getTaskSymbol() + " | " + encodedStatus + " | " + this.description;
    }

    public boolean containsQuery(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        return "[" + this.taskType.getTaskSymbol() + "]" + "[" + this.getStatusIcon() + "] " + this.description;
    }
}
