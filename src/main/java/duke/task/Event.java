package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * Task that is happening at a certain date.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Event extends Task {

    protected LocalDate at;

    /**
     * Instantiates an event with its date of occurrence.
     *
     * @param description Description of the event.
     * @param at Date of the event occurrence.
     * @throws DukeException If the input date is invalid.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date/time format. Please use this format: yyyy-mm-dd");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDate() + ")";
    }

    private String formattedDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
