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
    private String at;
    private LocalDate date;

    /**
     * Constructor for the event.
     *
     * @param description Event description.
     * @param at Time that the event happens in String format.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    /**
     * Constructor for the event.
     *
     * @param description Event description.
     * @param at Time that the event happens in LocalDate format.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.date = at;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return String representation of event.
     */
    @Override
    public String toString() {
        String eventDate = this.at == null
                ? this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) : this.at;
        return "[E]" + super.toString() + " (at: " + eventDate + ")";
    }
}
//@@author