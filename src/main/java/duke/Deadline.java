package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A Task class to indicate a deadline hence,
 * it requires a description of task
 * and by timing to indicate the deadline.
 */
public class Deadline extends Task {
    protected String by;
    public Deadline(String description,String by) {
        super(description);
        this.by = by;
    }


    /**
     * Returns the string representation of a deadline
     * task with the time it is to be done by.
     *
     * @return String representation
     * @throws DateTimeException if format specified for DateTime is wrong
     */
    @Override
    public String toString() throws DateTimeException {
        try {
            LocalDate d1 = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String deadline = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + " (by: " + deadline + ")";

        } catch (DateTimeParseException e) {
            return "[D]" + super.toString() + by ;
        }
    }



}