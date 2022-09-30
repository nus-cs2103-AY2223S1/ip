package duke.tasks;

import java.time.LocalDateTime;

import duke.tools.Parser;

/**
 * This class encapsulates a task with a deadline from the user.
 */
public class Deadline extends TaskWithDateTime {
    /**
     * Constructs a Deadline task.
     *
     * @param description Description of the task.
     * @param dateTime Due date and time of the task.
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE, dateTime);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", getTaskIcon(), super.toString(),
                Parser.formatDateTimeToPrint(this.getDateTime()));
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
