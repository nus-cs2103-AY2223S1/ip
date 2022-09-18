package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 *
 * @author njxue
 * @version v0.1
 */
public class Deadline extends Task {
    /**
     * Creates a Deadline object.
     *
     * @param description Description of the deadline task.
     * @param byDateTime Deadline of the deadline task.
     */
    public Deadline(String description, LocalDateTime byDateTime) {
        super(description, byDateTime);
    }

    /**
     * Returns the human-readable, string representation of the deadline task.
     *
     * @return Human-readable, string representation of the deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        String formattedDateTime = getDateTime().format(formatter);
        return String.format("[D]%s (by: %s)", super.toString(), formattedDateTime);
    }

    /**
     * Returns the formatted deadline task, which is to be written into the storage file.
     *
     * @return Formatted deadline task, which is to be written into the storage file.
     */
    @Override
    public String toFileFormatString() {
        return "D" + super.toFileFormatString() + getDescription() + "|" + getDateTime();
    }
}
