package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A task with a specified deadline.
 */
public class Deadline extends Task {

    private LocalDate time;

    /**
     * Constructs a deadline object.
     *
     * @param title The given description of the deadline.
     * @param time The time of the deadline.
     * @param done Whether the task has been done.
     */
    public Deadline(String title, String time, boolean done) throws DukeException {
        super(title, "deadline", done);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            this.time = LocalDate.parse(time, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please make sure that the date format is: 'yyyy-MM-dd'");
        }
    }

    /**
     * Returns the string formatted version of the deadline.
     *
     * @return The corresponding string.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return (super.toString() + " (by: " + this.time.format(formatter) + ")");
    }

    /**
     * Gets the time of the deadline in format of "yyyy-MM-dd".
     *
     * @return The time of the deadline as a string.
     */
    public String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return this.time.format(formatter);
    }
}
