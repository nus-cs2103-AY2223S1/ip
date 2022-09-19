package duke.task;

import java.time.LocalDateTime;

/**
 * Represents a task that is associated with a date.
 */
abstract public class DatedTask extends Task {

    /**
     * Initialises a Task with its description.
     *
     * @param description Description of the Task.
     */
    public DatedTask(String description) {
        super(description);
    }

    /**
     * Checks if another DatedTask occurs later or earlier than self.
     * @param task Task to compare to.
     * @return returns 1 if self is later than task, -1 if task if it is earlier.
     */
    public abstract int compareTo(DatedTask task);

    /**
     * Gets the date the task occurs/is due.
     * @return LocalDateTime of date.
     */
    public abstract LocalDateTime getDate();

}
