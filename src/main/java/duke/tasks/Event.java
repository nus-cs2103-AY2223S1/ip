package duke.tasks;

import java.time.LocalDateTime;

import duke.tools.Parser;

/**
 * This class encapsulates an event from the user.
 */
public class Event extends TaskWithDateTime {
    /**
     * Constructs an Event task.
     *
     * @param description Description of the event.
     * @param dateTime The date and time of the event.
     */
    public Event(String description, LocalDateTime dateTime) {
        super(description, TaskType.EVENT, dateTime);
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s (at: %s)", getTaskIcon(), super.toString(),
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
        if (o instanceof Event) {
            Event that = (Event) o;
            return getDescription().equals(that.getDescription())
                    && getDateTime().isEqual(that.getDateTime());
        }
        return false;
    }
}
