package duke.tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.utils.DateTime;

/**
 * Describes the event class.
 */
public class Event extends Task {
    private static final DateTimeFormatter formatter = DateTime.FORMATTER;
    private final LocalDateTime time;

    /**
     * Describes the constructor when taking user input.
     * @param description description of the event.
     * @param time the time of the event.
     */
    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, formatter);
    }

    /**
     * Describes the constructor when reading from database.
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
        return String.join("|", "E", Boolean.toString(isMarked), description, time.toString());
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + this.time.format(formatter) + ")";
    }
}
