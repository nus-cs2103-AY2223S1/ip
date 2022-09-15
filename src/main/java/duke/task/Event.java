package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event class is a subclass of Task that has a date and time for the Event to occur at.
 */
public class Event extends Task {

    /**
     * Stores the date of the event.
     */
    protected LocalDate eventDate;
    
    /**
     * Stores the time of the event
     */
    protected LocalTime eventTime;

    /**
     * Constructor of Event class
     * @param description Description of the Event.
     * @param eventDate Date of the Event.
     * @param eventTime Time of the Event.
     */
    public Event(String description, String eventDate, String eventTime) {
        super(description);
        this.eventDate = LocalDate.parse(eventDate);
        this.eventTime = LocalTime.parse(eventTime);
    }

    /**
     * Getter for the Event date and Time
     */
    @Override
    public String getDue() {
        return this.eventDate + " " + this.eventTime;
    }

    /**
     * Returns a String literal "E" for the Task type.
     */
    @Override
    public String getTaskType() {
        return "E";
    }

    /**
     * Overrides the parent class Task's toString() method to include its Task type and Event date and time.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + eventDate.format(DateTimeFormatter.ofPattern("d MMM yyyy")) + " " + eventTime + ")";
    }
}
