package duke.task;

import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected String description;
    private boolean isDone;
    protected static DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yy");
    protected static DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("dd MMM yy");
    protected static DateTimeFormatter INPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("HHmm");
    protected static DateTimeFormatter OUTPUT_TIME_FORMAT = DateTimeFormatter.ofPattern("h.mma");

    public abstract String format();

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }
}
