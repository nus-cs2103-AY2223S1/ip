package duke.tasks;

import java.time.LocalDateTime;

/**
 * This abstract class encapsulates a task that has a date and time.
 */
public abstract class TaskWithDateTime extends Task {
    /** The date and time of the event. */
    private LocalDateTime dateTime;

    /**
     * Constructs a Task object.
     *
     * @param description Description of the task.
     * @param taskType Type of task.
     * @param dateTime The date and time of the task.
     */
    public TaskWithDateTime(String description, TaskType taskType, LocalDateTime dateTime) {
        super(description, taskType);
        this.dateTime = dateTime;
    }

    /**
     * Gets the date and time of the task.
     *
     * @return The date and time of the task.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Sets the date and time of the task.
     *
     * @param dateTime The new date and time of the task.
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
