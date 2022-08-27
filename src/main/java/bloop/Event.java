package bloop;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task that is an event.
 */
public class Event extends Task {

    private String at;
    private LocalDateTime dateTime;

    /**
     * Constructor for an event object.
     *
     * @param task Task to be performed.
     * @param at Time of the event.
     */
    public Event(String task, String at) {
        super(task);
        this.at = at;
        this.dateTime = LocalDateTime.parse(at.trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy kkmm"));
    }

    public String getBy() {
        return at;
    }

    public String getDateTime() {
        return formatDateTime(dateTime);
    }

    public char getType() {
        return 'E';
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + getDateTime() + ")";
    }
}
