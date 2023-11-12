package duke.task;

import java.time.LocalDateTime;

import duke.DateConverter;

/**
 * Event, a type of Task
 */
public class Event extends Task {
    private final LocalDateTime time;

    /**
     * Constructor for Event object.
     * @param description Description of event.
     * @param time date and time of event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time);
    }

    /**
     * Converts Event object into String form to save.
     * @return E | {status} | {description} | {duration}
     */
    @Override
    public String storedString() {
        return "E | " + super.storedString() + " | " + time;
    }

    /**
     * Converts Event object into String form to display.
     * @return String representation of Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + DateConverter.convertTimeToString(time) + ")";
    }
}
