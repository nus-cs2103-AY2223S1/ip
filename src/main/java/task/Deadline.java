package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline is a type of task that has a deadline for task and
 * a description of the deadline.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Create new Deadline
     * @param description description of deadline
     * @param date date of deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        by = date;
    }

    /**
     * Create new Deadline
     * @param description description of deadline
     * @param date date of deadline
     * @param isDone boolean that is true when deadline is marked done otherwise it is false
     */
    public Deadline(String description, LocalDate date, boolean isDone) {
        super(description, isDone);
        by = date;
    }

    /**
     * Set date of deadline to given date
     * @param date Date that the deadline is to be set to
     */
    public void setBy(LocalDate date) {
        by = date;
    }

    @Override
    public String getSaveFormat() {
        return "D"
                + " | "
                + (getIsDone() ? 1 : 0)
                + " | "
                + getDescription()
                + " | "
                + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + System.lineSeparator();
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy"))
                + ")";
    }
}
