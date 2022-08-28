package duke;

import java.time.LocalDate;

/**
 * An abstraction for a task in Duke.
 */
public abstract class Task {
    private String description;
    private boolean marked;

    public Task(String description) {
        this.description = description;
        this.marked = false;
    }

    public abstract String getType();
    public abstract LocalDate getDate();

    public String getDescription() {
        return description;
    }

    public boolean getMarked() {
        return this.marked;
    }

    public void setMarked(boolean b) {
        this.marked = b;
    }

    @Override
    public String toString() {
        return "[" + (marked ? "X" : " ") + "] " + description;
    }
}
