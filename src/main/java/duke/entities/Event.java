package duke.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Event type of Task, which takes in /at flag.
 */
public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/[uuuu][uu] HHmm");
    private static final DateTimeFormatter printFormatter = DateTimeFormatter.ofPattern(("d/M/yyyy HHmm"));
    private final LocalDateTime time;

    /**
     * Constructs a new Event with Name and Time.
     * @param name Name of Event
     * @param time Time of Event
     */
    public Event(String name, String time) {
        super(name);
        this.time = LocalDateTime.parse(time, formatter);
    }

    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.time.format(printFormatter) + ")";
    }
}
