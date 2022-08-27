package duke.tasks;

import duke.utils.DateTime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Describes the event class.
 */
public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTime.formatter;
    private final LocalDateTime time;

    /**
     * Constructor when taking user input.
     * @param description description of the event.
     * @param time the time of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, formatter);
    }

    /**
     * Constructor when reading from database.
     * @param isMarked boolean value of whether the task is marked.
     * @param description description of the event.
     * @param time the time of the event.
     */
    public Event(boolean isMarked, String description, String time) {
        super(isMarked, description);
        this.time = LocalDateTime.parse(time);
    }

    @Override
    public String dbRepresentation() {
        return String.join("|", "E", Boolean.toString(isMarked),  description, time.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time.format(formatter) + ")";
    }
}
