package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//@@author chengda300
//Reused from https://nus-cs2103-ay2223s1.github.io/website/schedule/week2/project.html
// with minor modifications

/**
 * Represents an event. An event has a description and a time that it will happen.
 */
public class Event extends Task {
    private String eventTime;
    private LocalDate date;

    /**
     * Constructor for the event.
     *
     * @param description Event description.
     * @param eventTime that the event happens in String format.
     */
    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
        assert this instanceof Task : "Event should be a task.";
    }

    /**
     * Constructor for the event.
     *
     * @param description Event description.
     * @param eventTime Time that the event happens in LocalDate format.
     */
    public Event(String description, LocalDate eventTime) {
        super(description);
        this.date = eventTime;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        String eventDate = this.eventTime == null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.eventTime;
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
//@@author
