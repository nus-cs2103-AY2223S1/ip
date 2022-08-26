import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task to be attended at a certain time.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Event extends Task {

    /**
     * Time at which the Event is attended.
     */
    protected LocalDate at;

    /**
     * A basic constructor to instantiate the Event.
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date/time format. Please use this format: yyyy-mm-dd");
        }
    }

    /**
     * Method that returns the description of the Event.
     *
     * @return The description of the Event along with its status.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + formattedDate() + ")";
    }

    /**
     * Formats the date into an alternative format.
     *
     * @return Alternative format of the date.
     */
    private String formattedDate() {
        return this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

}
