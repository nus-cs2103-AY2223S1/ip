package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Task {
    private final String description;
    private boolean isDone = false;

    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String date) {
        this.description = description;
        try {
            this.date = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            throw new DukeException(e.getMessage());
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
}