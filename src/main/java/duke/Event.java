package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * An Event class containing an event description and date.
 * Inherits from the Task class.
 *
 */
public class Event extends Task {
    protected String description;
    protected boolean isDone;
    protected String at;
    LocalDate localDate;

    /**
     * Constructor for an Event object.
     * Converts both MMM d yyy and yyyy-MM-dd format to yyyy-MM-dd format.
     */
    public Event(String description, String at) throws DukeException {
        //throws DukeException when the date is wrong telling user to change date format
        //event breakfast /at 2022-12-12
        super(description);
        this.at = at;
        assert at != null : "at cannot be null!";
        try {
            //reverts the format for file reading
            //not good to use exceptions as conditions though...
            this.localDate = LocalDate.parse(at.trim(),
                    DateTimeFormatter.ofPattern("MMM d yyyy"));
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String newFormat = dtFormatter.format(localDate);
            this.at = newFormat;
            this.localDate = LocalDate.parse(this.at);
        } catch (DateTimeParseException e) {
            try {
                this.localDate = LocalDate.parse(at.trim(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            } catch (DateTimeParseException e1) {
                throw new DukeException("Please provide the date " +
                        "in the correct format, which is yyyy-mm-dd");

            }
        }
    }

    /**
     * Returns the Event object in string.
     *
     * @return String representation of the Event object.
     */
    @Override
    public String toString() {
        DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String newFormat = dtFormatter.format(localDate);
        return "[E]" + super.toString() + " (at: " + newFormat + ")";
    }
}
