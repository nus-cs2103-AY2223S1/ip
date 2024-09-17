package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event class to represent tasks with a time attached.
 */
public class Event extends Task {
    protected String timeString;
    protected LocalDate time;

    /**
     * Constructor for an Event.
     *
     * @param description description of the event.
     * @param time time of event in YYYY-MM-DD format.
     */
    public Event(String description, String time) {
        super(description);
        this.timeString = time;
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toWrite() {
        return String.format("E~%s~%s~%s", (isDone ? "1" : "0"), description.trim(), timeString);
    }
}
