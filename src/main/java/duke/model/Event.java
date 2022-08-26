package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task with the activity, start date and end date specified.
 */
public class Event extends Task {

    protected LocalDate startDate;
    protected LocalDate endDate;

    /**
     * A constructor for an Event.
     *
     * @param description
     * @param startDate
     * @param endDate
     */
    public Event(String description, String startDate, String endDate) {
        super(description);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    /**
     * Returns a formatted string of an Event to be stored in the storage.
     *
     * @return a formatted string of an Event for storage
     */
    @Override
    public String toStorage() {
        return "E | " + (this.isDone ? 1 : 0) + " | " + description + " | "
                + this.startDate + " | " + this.endDate + "\n";
    }

    /**
     * Returns a string representation of an Event.
     *
     * @return a string representing an Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.startDate.format(DateTimeFormatter.ofPattern("d MMM YYYY"))
                + " to " + this.endDate.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}
