package duke.task;

import duke.exceptions.InvalidDateTimeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Task {
    private final String description;
    private boolean isDone = false;

    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.date = LocalDate.parse("1970-01-01");
    }

    public Task(String description, String date) {
        this.description = description;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(e.getMessage());
        }
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDate() {
        return this.date.toString();
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + this.description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return isDone == task.isDone && description.equals(task.description) && date.equals(task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, isDone, date);
    }
}