package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Task with a period of time of the task
 */
public class Event extends Task {
    /**
     * Event object at field which indicates the timings
     */
    protected LocalDate atTime;

    /**
     * A constructor to initialize the Event object with the description and timings
     *
     * @param description The task
     * @param atTime The timings
     */
    public Event(String description, LocalDate atTime) {
        super(description);
        this.atTime = atTime;
    }

    /**
     * Returns a string that represents the Event
     * @return String A string that represents the current object
     */
    @Override
    public String toString() {
        String timing = this.atTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E][" + this.getStatusIcon() + "] " + super.toString() + " (at: " + timing + ")";
    }

    /**
     * Returns a string that represents the Event in text file format
     * @return String A string that represents the current object
     */
    @Override
    public String toFileString() {
        String timing = this.atTime.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "E | " +  this.getFileStatus() + " | " + super.toString() + " | " + timing;
    }

    /**
     * Changes the date of the Event
     *
     * @param date The new date
     */
    public void changeDate(LocalDate date){
        this.atTime = date;
    }
}
