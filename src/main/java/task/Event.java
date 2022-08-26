package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event tasks at a certain date
 */
public class Event extends Task {
    private LocalDate datetime;

    /**
     * Event constructor with the specified description and datetime
     *
     * @param description a {@link String} indicating the event description
     * @param datetime a {@link LocalDate} indicating the event date
     */
    public Event(String description, LocalDate datetime) {
        super(description);
        this.datetime = datetime;
    }

    /**
     * Deadline constructor with the specified isDone, description, and datetime
     *
     * @param isDone a {@link String} indicating if the deadline has been marked as done
     * @param description a {@link String} indicating the deadline description
     * @param datetime a {@link LocalDate} indicating the deadline date
     */
    public Event(String isDone, String description, LocalDate datetime) {
        super(isDone, description);
        this.datetime = datetime;
    }

    /**
     * Returns a {@link String} representation of an event
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + datetime.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns a {@link String} representation of an event
     *
     * @return String
     */
    @Override
    public String toTxt() {
        return String.format("E @@ %s @@ %s", super.toTxt(), datetime);
    }
}
