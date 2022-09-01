
package component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Public class Deadline that extends Task.
 */
public class Deadline extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructs an unmarked Deadline class.
     * @param dateTime Date and time the deadline needs to be done by
     * @param description Description of the deadline.
     */
    public Deadline(LocalDateTime dateTime, String description) {
        super(description, "D");
        this.dateTime = dateTime;
    }

    /**
     * Constructs a Deadline class.
     * @param dateTime Date and time the deadline needs to be done by
     * @param description Description of the deadline
     * @param isDone Status of the deadline
     */
    public Deadline(LocalDateTime dateTime, String description, boolean isDone) {
        super(description, "D", isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns a String format of date and time of the deadline
     * @return string formatted date and time
     */
    public String getDateTime() {
        return " (by: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    /**
     * Returns a LocalDateTime object that the deadline needs to be completed by
     * @return local date and time object
     */
    public LocalDateTime getRawDateTime() {
        return this.dateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String printText() {
        return super.printText() + " | " + this.getDateTime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}