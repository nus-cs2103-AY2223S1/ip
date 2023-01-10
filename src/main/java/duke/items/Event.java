package duke.items;

import java.time.format.DateTimeParseException;

/**
 * Object representing an Event Item.
 */
public class Event extends Item {
    /**
     * Create an incomplete Event Item.
     * @param name Item description.
     * @param eventTime Time of the event.
     * @throws DateTimeParseException If the input string for eventTime is not in correct format.
     */
    public Event(String name, String eventTime) throws DateTimeParseException {
        super(name, ItemTypes.EVENT, eventTime);
    }


    /**
     * Create an Event Item and set its completion status.
     * @param name Item description.
     * @param eventTime Time of the event.
     * @param isDone Completion status of the task.
     * @throws DateTimeParseException If the input string for eventTime is not in correct format.
     */
    public Event(String name, String eventTime, boolean isDone) throws DateTimeParseException {
        super(name, isDone, ItemTypes.EVENT, eventTime);
    }

    @Override
    public String toString() {
        return this.getItemType() + super.toString() + " (at: " + this.getDateTimeString() + ")";
    }
}
