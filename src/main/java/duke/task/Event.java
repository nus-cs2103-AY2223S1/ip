package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Event extends Task {
    private LocalDateTime dateTime;

    /**
     * Returns a Event object.
     * Throws a DukeException if the time is not parsable.
     *
     * @param taskName Name of the task
     * @param dateTime Date in dd-MM-yyyy HHmm format
     * @throws DukeException If date is not parsable
     */
    public Event(String taskName, String dateTime) throws DukeException {
        super(taskName);
        try {
            this.dateTime = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("☹ OOPS!!! Cannot parse date. Enter date according to example, 02-12-2019 1800");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.dateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + "|" + (this.getTaskStatus() ? "1" : "0") + "|" + this.getTaskName() + "|"
                + this.dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
