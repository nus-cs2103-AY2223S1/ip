package duke.task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A class that handles the deadline tasks.
 */
public class Deadline extends DatedTask {

    /**
     * Constructs the deadline tasks.
     *
     * @param description the description of the deadline tasks.
     * @param by the date that the task has to be done by.
     * @throws DateTimeException thrown when wrong date format is given.
     */
    public Deadline(String description, LocalDate by) throws DateTimeException {
        super(description, by);
    }

    /**
     * Returns the string of deadline.
     *
     * @return the string of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
