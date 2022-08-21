package duke;

import java.time.LocalDate;

/**
 * Abstract class that all Tasks inherit from.
 */
public abstract class Task {
    private String description;
    private boolean done;

    public Task(String description) {
        this.description = description;
        this.done = false;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Mark task as not done.
     */
    public void unmarkDone() {
        this.done = false;
    }

    /**
     * Return common part of all tasks for file representation
     * @return "{1 if done else 0} | {description}"
     */
    public String toFileRepresentation() {
        return String.format("%d | %s", this.done ? 1 : 0, this.description);
    }

    /**
     * Returns nothing because abstract tasks should not be created.
     * @param rep String of task representation
     * @return Nothing.
     */
    public static Task fromFileRepresentation(String rep) {
        return null;
    }

    /**
     * Returns if tasks is on that date.
     * If no date, default to false.
     * @param date Specified date.
     * @return Whether the task is on the specified date.
     */
    public boolean isOn(LocalDate date) {
        return false;
    }

    /**
     * Return String representation of Task.
     * @return "{[X] if done else [ ]} | {description}"
     */
    @Override
    public String toString() {
        char doneFlag = done ? 'X' : ' ';
        return String.format("[%c] %s", doneFlag, description);
    }
}
