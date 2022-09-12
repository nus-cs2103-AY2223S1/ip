package duke;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

/**
 * A task that needs to be done before a specific date/time.
 *
 * @author Lai Han Wen
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for a Deadline task.
     *
     * @param description Description of Deadline.
     * @param by Date of deadline, in yyyy-mm-dd format, e.g. 2020-08-25.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the String representation of a deadline task, where the
     * date is in format month_day_year, e.g., Oct 15 2019.
     *
     * @return The String representation of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns the String representation of a deadline task, where the
     * date is in format yyyy-mm-dd, e.g., 2020-08-25.
     *
     * @return The String representation of a deadline task.
     */
    public String toStringOri() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
