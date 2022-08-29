package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime dateTime;

    public Event(LocalDateTime dateTime, String description) {
        super(description, "E");
        this.dateTime = dateTime;
    }

    public Event(LocalDateTime dateTime, String description, boolean isDone) {
        super(description, "E", isDone);
        this.dateTime = dateTime;
    }

    /**
     * Returns a String format of date and time of the event
     * @return string formatted date and time
     */
    public String getDateTime() {
        return " (at: " +
                this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy', ' hh:mm a")) + ")";
    }

    /**
     * Returns a LocalDateTime object that the event needs to be completed by
     * @return local date and time object
     */
    public LocalDateTime getRawDateTime() {
        return this.dateTime;
    }

    /**
     * Returns a String format of this event, to be written to TXT file
     * For example:
     * E |   | CS2103 | (at: Aug 29 2022, 09:30 PM)
     * @return string formatted event
     */
    @Override
    public String printText() {
        return super.printText() + " | " + this.getDateTime();
    }

    @Override
    public String toString() {
        return "[" + this.getCode() + "]" + super.toString() + this.getDateTime();
    }
}