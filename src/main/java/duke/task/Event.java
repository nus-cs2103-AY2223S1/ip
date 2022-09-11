package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task occurring at a date and time.
 *
 * @author njxue
 * @version v0.1
 */
public class Event extends Task {
    /**
     * Creates an Event object.
     *
     * @param description Description of the event task.
     * @param atDateTime Date and time of the event task.
     */
    public Event(String description, LocalDateTime atDateTime) {
        super(description, atDateTime);
    }

    /**
     * Returns the human-readable, string representation of the event task.
     *
     * @return Human-readable, string representation of the event task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String formattedDateTime = getDateTime().format(formatter);
        return String.format("[E]%s (at: %s)", super.toString(), formattedDateTime);
    }

    /**
     * Returns the formatted event task, which is to be written into the storage file.
     *
     * @return Formatted event task, which is to be written into the storage file.
     */
    @Override
    public String toFileFormatString() {
        return "E" + super.toFileFormatString() + getDescription() + "|" + getDateTime();
    }
}
