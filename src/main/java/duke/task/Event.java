package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.duke.DukeException;

/**
 * Represents a Event Task.
 */
public class Event extends Task {
    /**
     * Represents the end date of the Deadline task.
     */
    private LocalDateTime dateTime;

    /**
     * Returns an Event object.
     * Throws a DukeException if the time is not parsable.
     *
     * @param taskName Name of the task
     * @param dateTime Date in dd-MM-yyyy HHmm format
     * @throws DukeException If date is not parsable
     */
    public Event(String taskName, String dateTime) throws DukeException {
        super(taskName);
        assert !dateTime.isEmpty() : "date time should not be empty";
        try {
            if (dateTime.charAt(dateTime.length() - 1) == ' ') {
                dateTime = dateTime.substring(0, dateTime.length() - 1);
            }
            this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("OOPS!!! Cannot parse date. Enter date according to example, 02-12-2019 1800");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toFileString() {
        return "E" + "|" + (this.getTaskStatus() ? "1" : "0") + "|" + this.getPriorityNumber() + "|"
                + this.getTaskName() + "|"
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
