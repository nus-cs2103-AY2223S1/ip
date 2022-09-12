package duke;

import java.time.LocalDate;

/**
 * An abstraction for a task in Duke.
 */
public abstract class Task {
    private String description;
    private boolean isMarked;

    /**
     * Constructor for the Task class.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isMarked = false;
    }

    public abstract String getType();
    public abstract LocalDate getDate();

    public String getDescription() {
        return description;
    }

    public boolean getMarked() {
        return this.isMarked;
    }

    public void setMarked(boolean b) {
        this.isMarked = b;
    }

    @Override
    public String toString() {
        return "[" + (isMarked ? "X" : " ") + "] " + description;
    }
}
