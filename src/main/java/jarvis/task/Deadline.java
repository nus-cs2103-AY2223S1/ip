package jarvis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Represents a Deadline which is a subclass of Task.
 *
 */
public class Deadline extends Task{
    public LocalDate by;

    /**
     * Returns a new Deadline Object with the given description and date.
     *
     * @param description Description of the task to be done by deadline.
     * @param date Date of the Deadline.
     */
    public Deadline(String description, String date) {
        super(description);
        this.by = LocalDate.parse(date);
    }

    /**
     * Returns String description of Deadline in the following format:
     * [D] *Deadline Description* (by: *date*)
     * @return String description of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
