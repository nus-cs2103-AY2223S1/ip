package duke.task;

import java.time.LocalDateTime;

import duke.parser.DateParser;

/**
 * Event task.
 */
public class Event extends Task {
    private LocalDateTime at;

    /**
     * Constructor for Event.
     * @param description
     * @param at
     */
    public Event(String description, LocalDateTime at) {
        super(description);
        this.at = at;
    }

    public Event(String description, String tag, LocalDateTime at) {
        super(description, tag);
        this.at = at;
    }

    /**
     * String format of Deadline.
     * @return
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateParser.convertDateTimeToString(at) + ")";
    }

    /**
     * Convert an Event to a String to store with Storage.
     * @return
     */
    @Override
    public String toMemoryString() {
        return "E" + super.toMemoryString() + DateParser.convertDateTimeToMemoryString(at);
    }
}
