package duke.task;

import java.time.LocalDateTime;
import java.util.Optional;

import duke.util.ParsedData;

/**
 * Parent task class for all other Tasks.
 */
public abstract class Task implements Comparable<Task> {
    protected final String description;
    protected boolean completed;
    protected Optional<LocalDateTime> dateTime;

    protected Task(String description, Optional<LocalDateTime> dateTime) {
        this.description = description;
        this.completed = false;
        this.dateTime = dateTime;
    }

    protected Task(String description) {
        this(description, Optional.empty());
    }

    /**
     * Marks the task as complete.
     */
    public void mark() {
        completed = true;
    }

    /**
     * Marks the task as incomplete.
     */
    public void unmark() {
        completed = false;
    }

    /**
     * Check if task is completed.
     * 
     * @return
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * {@inheritDoc} Compares order based on data assigned to task.
     * 
     * @param o The other task
     * @return a task is bigger if its time higher and biggest if non-existent
     */
    @Override
    public int compareTo(Task o) {
        if (!o.dateTime.isPresent()) {
            return -1;
        } else if (!dateTime.isPresent()) {
            return 1;
        }

        return dateTime.get().compareTo(o.dateTime.get());
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
     * Returns the [complete status] description.
     * 
     * @return String
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", (completed) ? "X" : " ", description);
    }

    /**
     * Convert the current task data into parsedata to be saved.
     * 
     * @return converted Task
     */
    abstract public ParsedData convertToParseData();

}
