package duke;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * A Deadline class containing an event description and date.
 * Inherits from the Task class.
 *
 */
public class Deadline extends Task {
    protected String description;
    protected boolean isDone;
    protected String date;
    LocalDate localDate;


    /**
     * Constructor for a Deadline object.
     * Converts both MMM d yyy and yyyy-MM-dd format to yyyy-MM-dd format.
     */
    public Deadline(String description, String date) throws DukeException{
        //throws DukeException when the date is wrong telling user to change date format
        //deadline breakfast /by 2022-12-12
        super(description);
        this.date = date;
        assert date != null : "date cannot be null!";
        try {
            //reverts the format for file reading
            //not good to use exceptions as conditions though...
            this.localDate = LocalDate.parse(date.trim(),
                    DateTimeFormatter.ofPattern("MMM d yyyy"));
            DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String newFormat = dtFormatter.format(localDate);
            this.date = newFormat;
            this.localDate = LocalDate.parse(this.date);
        } catch (DateTimeParseException e) {
            try {
                this.localDate = LocalDate.parse(date.trim(),
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
        return "[D]" + super.toString() + " (by: " + newFormat + ")";
    }
}
