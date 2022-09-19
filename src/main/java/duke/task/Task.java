package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.util.ParsedData;

/**
 * Parent task class for all other Tasks.
 */
public abstract class Task implements Comparable<Task> {
    protected final String description;
    protected boolean isComplete;
    protected Optional<LocalDateTime> dateTime;

    protected Task(String description, Optional<LocalDateTime> dateTime) {
        this.description = description;
        this.isComplete = false;
        this.dateTime = dateTime;
    }

    protected Task(String description) {
        this(description, Optional.empty());
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        isComplete = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        isComplete = false;
    }

    /**
     * Check if task is completed.
     *
     * @return
     */
    public boolean isCompleted() {
        return isComplete;
    }

    /**
     * {@inheritDoc} Compares order based on data assigned to task.
     *
     * @param task The other task
     * @return a task is bigger if its time higher and biggest if non-existent
     */
    @Override
    public int compareTo(Task task) {
        if (!task.containsDatetime()) {
            return -1;
        } else if (!containsDatetime()) {
            return 1;
        }

        return dateTime.get().compareTo(task.dateTime.get());
    }

    /**
     * Useful comparison with time directly
     *
     * @param o
     * @return int
     */
    public int compareTo(LocalDateTime o) {
        if (!dateTime.isPresent()) {
            return 1;
        }

        return dateTime.get().compareTo(o);
    }

    /**
     * Checks if the task contains a completion time stamp
     *
     * @return true if task contains datetime
     */
    public boolean containsDatetime() {
        return dateTime.isPresent();
    }


    /**
     * Returns the [complete status] description.
     *
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", (isComplete) ? "X" : " ", description);
    }

    /**
     * Convert the current task data into parsedata to be saved.
     *
     * @return converted Task
     */
    public abstract ParsedData convertToParseData();

}
