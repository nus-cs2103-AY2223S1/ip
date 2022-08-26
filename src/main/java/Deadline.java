import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task to be completed by a certain time.
 * CS2103T iP
 * AY22/23 Semester 1
 *
 * @author Perry Wong
 */
public class Deadline extends Task {

    /**
     * Time that the task needs to be completed by.
     */
    protected LocalDate by;

    /**
     * A basic constructor to instantiate the Deadline.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new DukeException("Wrong date/time format. Please use this format: yyyy-mm-dd");
        }
    }

    /**
     * Method that returns the description of the Deadline.
     *
     * @return The description of the Event along with its status.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + formattedDate() + ")";
    }

    /**
     * Formats the date into an alternative format.
     *
     * @return Alternative format of the date.
     */
    private String formattedDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
