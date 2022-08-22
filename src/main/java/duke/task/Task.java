package duke.task;

import java.time.LocalDate;

/**
 * The abstract superclass of all types of tasks the user can input.
 */
public abstract class Task {
    /**
     * Represents the type of task it is.
     */
    public final TaskType type;
    protected String description;
    protected boolean isDone;
    protected Task(String description, TaskType type) {
        this.description = description;
        this.isDone = false;
        this.type = type;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public Integer getDoneStatus() {
        if (this.isDone) {
            return 1;
        } else {
            return 0;
        }
    }

    public String getDescription() {
        return this.description;
    }

    public abstract boolean isDateEqual(LocalDate date);
    public boolean isQueryPresent(String query) {
        return this.description.contains(query);
    }

    @Override
    public String toString() {
        if (this.isDone) {
            return "[X] " + this.description;
        } else {
            return "[ ] " + this.description;
        }
    }
}
