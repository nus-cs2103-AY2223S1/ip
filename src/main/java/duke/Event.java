package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event that has a date and/or time.
 */
public class Event extends Task {
    protected String at = "";
    protected LocalDate atDate;
    protected LocalTime atTime;

    /**
     * Constructor for the Event class.
     * @param description A string that provides information for the event.
     * @param at A string that provides information about when the event is.
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
        convertToDateTime(at);
    }

    /**
     * Another constructor for the Event class.
     * @param description A string that provides information for the event.
     * @param atDate A LocalDate object that provides information about the date of the event.
     * @param atTime A LocalTime object that provides information about the time of the event.
     */
    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    private void convertToDateTime(String at) {
        if (at.length() > 10) {
            int spacePos = at.indexOf(" ");
            String date = at.substring(0, spacePos);
            String time = at.substring(spacePos + 1);
            this.atDate = LocalDate.parse(date);
            this.atTime = LocalTime.parse(time);
        } else {
            this.atDate = LocalDate.parse(at);
        }
    }

    /**
     * Returns the event as a string.
     * @return The event as a string.
     */
    @Override
    public String toString() {
        return ("E | "
                + super.toString()
                + " | "
                + this.atDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ((this.atTime == null)
                        ? ""
                        : " | " + this.atTime.toString()));
    }
}
