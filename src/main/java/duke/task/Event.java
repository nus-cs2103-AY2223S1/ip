package duke.task;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Class that represents an event.
 */
public class Event extends Task {
    /** Date of the event */
    private LocalDate atDate;
    /** Date-time of the event */
    private LocalDateTime atDateTime;
    /** If event has a specified time */
    private final boolean hasTime;

    /**
     * Constructor to initialize the description, completion status and date of the event.
     * Completion status is always false when event is first created.
     * 
     * @param desc The event description.
     * @param atDate The event date.
     */
    public Event(String desc, LocalDate atDate) {
        super(desc);
        this.atDate = atDate;
        this.isDone = false;
        this.hasTime = false;
    }

    /**
     * Constructor to initialize the description, completion status and date-time of the event.
     * Completion status is always false when event is first created.
     * 
     * @param desc The event description.
     * @param atDateTime The event date-time.
     */
    public Event(String desc, LocalDateTime atDateTime) {
        super(desc);
        this.atDateTime = atDateTime;
        this.isDone = false;
        this.hasTime = true;
    }

    /**
     * Constructor to initialize the description, completion status and date of the event.
     *
     * @param desc The event description.
     * @param atDate The event date.
     * @param isDone The event completion status.
     */
    public Event(String desc, LocalDate atDate, boolean isDone) {
        super(desc, isDone);
        this.atDate = atDate;
        this.hasTime = false;
    }

    /**
     * Constructor to initialize the description, completion status and date-time of the event.
     *
     * @param desc The event description.
     * @param atDateTime The event date-time.
     * @param isDone The event completion status.
     */
    public Event(String desc, LocalDateTime atDateTime, boolean isDone) {
        super(desc, isDone);
        this.atDateTime = atDateTime;
        this.hasTime = true;
    }

    private String formatAt() {
        if (this.hasTime) {
            return this.atDateTime.format(DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a"));
        } else {
            return this.atDate.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
    }
    
    private String saveFormatAt() {
        if (this.hasTime) {
            return this.atDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else {
            return this.atDate.format(DateTimeFormatter.ofPattern("d/M/yyyy"));
        }
    }

    /**
     * Converts the event to its saved format.
     * 
     * @return The string representation of the saved format of the event.
     */
    @Override
    public String getSaveFormat() {
        return "E " + super.getSaveFormat() + " | " + this.saveFormatAt();
    }

    /**
     * Returns the string representation of the event.
     * 
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.formatAt() + ")";
    }
}
