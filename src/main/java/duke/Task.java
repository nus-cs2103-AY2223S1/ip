package duke;

import java.time.LocalDate;

/**
 * Abstract class that all Tasks inherit from.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a task.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return whether the task is done.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns common part of all tasks for file representation.
     *
     * @return "{1 if done else 0} | {description}".
     */
    public String toFileRepresentation() {
        return String.format("%d | %s", this.isDone ? 1 : 0, this.description);
    }

    /**
     * Returns nothing because abstract tasks should not be created.
     *
     * @param rep String of task representation.
     * @return Nothing.
     */
    public static Task fromFileRepresentation(String rep) {
        return null;
    }

    /**
     * Returns if tasks is on that date.
     * If no date, default to false.
     *
     * @param date Specified date.
     * @return Whether the task is on the specified date.
     */
    public boolean isOn(LocalDate date) {
        return false;
    }

    /*
     * Return whether the description includes the search query.
     *
     * @param searchQuery String to search.
     * @return whether the description includes the search query.
     */
    public boolean descriptionIncludes(String searchQuery) {
        return description.contains(searchQuery);
    }

    /**
     * Returns String representation of Task.
     *
     * @return "{[X] if done else [ ]} | {description}"
     */
    @Override
    public String toString() {
        char doneFlag = isDone ? 'X' : ' ';
        return String.format("[%c] %s", doneFlag, description);
    }
}
