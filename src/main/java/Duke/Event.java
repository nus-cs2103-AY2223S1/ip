package Duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Class that extends Task class.
 */
public class Event extends Task{
    protected String timing;
    protected String date;

    /**
     * Marks as a task as done and displays output messages.
     *
     * @param description name of the event.
     * @param date date that the event will take place.
     * @param timing time that the event has taken place.
     */
    public Event(String description, String date, String timing) {
        super(description);
        this.timing = timing;
        this.date  = date;
    }
    /**
     * String representation of an event that has been added to the list
     *
     * @returns String representation of the event
     */
    @Override
    public String toString() {
        LocalDate d1 = LocalDate.parse(this.date);
        return "[E]" + "[" + super.getStatusIcon() + "]" +  super.toString() + " (at: " + d1.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " + this.timing +  ")";
    }
}
