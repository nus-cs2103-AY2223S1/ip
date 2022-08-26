package duke.tasks;

import duke.tools.Parser;

import java.time.LocalDateTime;

/**
 * This class encapsulates a task with a deadline from the user.
 */
public class Deadline extends Task {

    /** Due date and time of the task. */
    private LocalDateTime dateTime;

    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param dateTime Due date and time of the task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE);
        this.dateTime = dateTime;
    }

    /**
     * Gets the due date and time of the task.
     *
     * @return The due date and time of the task.
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", getTaskIcon(), super.toString(),
                Parser.formatDateTimeToPrint(dateTime));
    }

    /**
     * Checks if the other object is equals to this one.
     *
     * @param o The other object to check on equality.
     * @return Boolean representing the equality of the object with this one.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Deadline) {
            Deadline that = (Deadline) o;
            return getDescription().equals(that.getDescription())
                    && getDateTime().isEqual(that.getDateTime());
        }
        return false;
    }
}
