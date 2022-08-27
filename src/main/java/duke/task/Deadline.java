package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Task that is due at a certain date.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Instantiates a deadline with its date to be completed by.
     *
     * @param description Description of the task.
     * @param by Deadline of the task.
     * @throws DukeException If the input date is invalid.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date/time format. Please use this format: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate() + ")";
    }

    private String formattedDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
