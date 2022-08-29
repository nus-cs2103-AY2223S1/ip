package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * Represents an Event Task
 */
public class Event extends Task {
    private String at;

    /**
     * Creates an Event Task object
     */
    public Event(String description, String at) throws DateTimeParseException {
        super(description);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy, h:mma");
        LocalDateTime dateTime = LocalDateTime.parse(at.trim(), inputFormatter);
        this.at = dateTime.format(outputFormatter);
    }

    /**
     * Creates an Event Task object, to be used
     * when loading from task file
     */
    public Event(String description, boolean isDone, String at) {
        super(description, isDone);
        this.at = at;
    }

    /**
     * Returns a string describing the Event object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    /**
     * Returns a string describing the Event object
     * to be used when saving to a task file
     */
    @Override
    public String toStringData() {
        return "E | " + super.toStringData() + " (" + at + ")";
    }
}
