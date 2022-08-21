package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.Ui;

/**
 * This class represents a deadline which is {@code by} a certain date.
 */
public class Deadline extends Task {
    /**
     * Constructs a deadline with a description and a date.
     *
     * @param description the description of the deadline
     * @param by the date of the deadline
     * @throws DukeException if the date is of invalid format
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.dateTime = LocalDateTime.parse(by, Ui.getInputFormatter());
        } catch (DateTimeParseException e) {
            try {
                this.dateTime = LocalDateTime.parse(by, Ui.getOutputFormatter());
            } catch (DateTimeParseException e2) {
                throw new DukeException("Invalid date format. Use yyyy-mm-dd HHmm.");
            }
        }
    }

    /**
     * Returns the description of the task, with a Deadline tag.
     *
     * @return Description of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + dateTime.format(Ui.getOutputFormatter()) + ")";
    }
}
