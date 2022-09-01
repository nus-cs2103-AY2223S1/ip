package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents an event on a date.
 */
public class Event extends Task {
    private final LocalDate date;

    /**
     * Returns an instance of Event.
     * @param isDone Is task complete.
     * @param description Description of task.
     * @param dateStr Date of event.
     */
    public Event(String isDone, String description, String dateStr) {
        super(description, isDone.equals("1"));
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            // Default current date will be used.
            parsedDate = LocalDate.now();
        }
        this.date = parsedDate;
    }

    /**
     * Returns string format of event for saving purpose.
     * @return String format of event.
     */
    @Override
    public String toStringSaveFormat() {
        return String.format("E,%s,%s,%s\n", this.isDone ? "1" : "0", this.description, this.date);
    }

    /**
     * Return string representation of event.
     * @return String representation of event.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)\n", this.isDone ? "X" : " ", this.description, this.date);
    }
}
