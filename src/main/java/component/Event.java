package component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Public class Event that extends Task.
 */
public class Event extends Task {
    protected LocalDateTime dateTime;

    /**
     * Constructs an unmarked Event class.
     * @param dateTime Date and time of the event
     * @param description Description of the event
     */
    public Event(LocalDateTime dateTime, String description) {
        super(description, "E");
        this.dateTime = dateTime;
    }

    /**
     * Constructs an Event class.
     * @param dateTime Date and time of the event
     * @param description Description of the event
     * @param isDone Status of the event
     */
    public Event(LocalDateTime dateTime, String description, boolean isDone) {
        super(description, "E", isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns date and time of the event in string format.
     * @return Date and time of the event
     */
    public String getDateTime() {
        return " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    /**
     * Returns a LocalDateTime object that the event needs to be completed by
     * @return Date and time of the event
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