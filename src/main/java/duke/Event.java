package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Encapsulates an event Class
 */
public class Event extends Task {
    private LocalDate date;


    /**
     * Constructs an event class
     * @param description The description of the event
     * @param date The date of the event
     */
    public Event(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns boolean value indicating if this event will be on a certain day
     * @param date the date being queried
     * @return Whether the event is on the date
     */
    public boolean onThisDate(LocalDate date) {
        return this.date.equals(date);
    }


    /**
     * Formats the description and date to be printed in the commandline
     * @return The formatted description and date
     */
    public String toStringDate() {
        return super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns the parsed version of the deadline to be stored in the text file.
     * @return the parsed Event
     */
    @Override
    public String parseTask() {
        return "E" + super.parseTask() + "/" + this.date;
    }


    /**
     * Returns the string representation of the Event
     * @return The string representation of the Event
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
