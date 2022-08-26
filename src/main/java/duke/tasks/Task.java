package duke.tasks;

import java.time.format.DateTimeParseException;
import java.util.Locale;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public boolean hasDescription(String description) {
        return this.description.toLowerCase().contains(description.toLowerCase());
    }

    public boolean isBefore(String deadline) throws DateTimeParseException { return false; }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
