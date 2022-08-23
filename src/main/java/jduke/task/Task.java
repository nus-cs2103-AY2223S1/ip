package jduke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    protected final String description;
    protected boolean isCompleted;

    protected static DateTimeFormatter inputDateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
    protected static DateTimeFormatter inputTimeFormatter = DateTimeFormatter.ofPattern("HHmm");
    protected static DateTimeFormatter outputDateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected static DateTimeFormatter outputTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

    protected Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    public void markAsDone() {
        this.isCompleted = true;
    }

    public void markAsUndone() {
        this.isCompleted = false;
    }

    protected abstract String getType();

    public abstract boolean isEqualDate(LocalDate date);

    public abstract String toStorageFormat();
}
