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
        String type = "[E]";
        String taskDescription = super.toString();
        String date = " (at: " + formattedDate() + ")";
        return type + taskDescription + date;
    }

    private String formattedDate() {
        DateTimeFormatter printedFormat = DateTimeFormatter.ofPattern("MMM d yyyy");
        return this.at.format(printedFormat);
    }

}
