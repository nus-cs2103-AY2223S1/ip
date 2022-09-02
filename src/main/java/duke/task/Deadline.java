package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

public class Deadline extends Task {
    private LocalDateTime endDate;

    /**
     * Returns a Deadline object.
     * Throws a DukeException if the endDate is not parsable.
     *
     * @param taskName Name of the task
     * @param endDate  Date in dd-MM-yyyy HHmm format
     * @throws DukeException If date is not parsable
     */
    public Deadline(String taskName, String endDate) throws DukeException {
        super(taskName);
        try {
            this.endDate = LocalDateTime.parse(endDate, DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        } catch (DateTimeParseException dateTimeParseException) {
            throw new DukeException("☹ OOPS!!! Cannot parse date. Enter date according to example, 02-12-2019 1800");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.endDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "D" + "|" + (this.getTaskStatus() ? "1" : "0") + "|" + this.getTaskName() + "|"
                + this.endDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }
}
