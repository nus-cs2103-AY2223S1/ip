package duke.items;

import java.time.format.DateTimeParseException;

/**
 * Object representing a Deadline Item.
 */
public class Deadline extends Item {
    /**
     * Create an incomplete Deadline Item.
     * @param name Item description.
     * @param dueDate When the deadline is due.
     * @throws DateTimeParseException If the input string for dueDate is not in correct format.
     */
    public Deadline(String name, String dueDate) throws DateTimeParseException {
        super(name, ItemTypes.DEADLINE, dueDate);
    }

    /**
     * Create a Deadline Item and set its completion status.
     * @param name Item description.
     * @param dueDate When the deadline is due.
     * @param isDone Completion status of the task.
     * @throws DateTimeParseException If the input string for dueDate is not in correct format.
     */
    public Deadline(String name, String dueDate, boolean isDone) throws DateTimeParseException {
        super(name, isDone, ItemTypes.DEADLINE, dueDate);
    }

    @Override
    public String toString() {
        return this.getItemType() + super.toString() + " (by: " + this.getDateTimeString() + ")";
    }
}
