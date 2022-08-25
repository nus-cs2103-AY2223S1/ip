package puke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A type of task, called an Event
 */
public class Event extends Task {
    /**
     * Date of event, stored as LocalDate
     */
    protected  LocalDate date;

    /**
     * Creates an Event task
     * @param description what the ask is
     * @param date LocalDate object of when the event is
     */
    public Event (String description, LocalDate date){
        super(description);
        this.date = date;
    }

    /**
     * Get string representation of Event
     * @return string representation of Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Gets the format to save Event on hard disk
     * @return String representation of Event save format
     */
    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "E | 1 | " + this.description + " | " + this.date;
        } else {
            return "E | 0 | " + this.description + " | " + this.date;
        }
    }
} 
