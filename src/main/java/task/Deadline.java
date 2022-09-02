package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * deadline is a Task that needs to be done by a specific date/time.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructor for a deadline that takes in the task description and the deadline
     * @param description the specifics of the task
     * @param by the date in yyyy-MM-dd that the task is due
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * stringify is used to store the tasks in a standard format in a storage file
     * @return string representation of deadline to be stored
     */
    @Override
    public String stringify() {
        return String.format("D##%s##%s", super.stringify(), this.by);
    }

    /**
     * toString is used to print out the task in an easily readable format
     * @return string representation of a deadline
     */
    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.by.format(DateTimeFormatter.ofPattern("dd MMM yyyy")));
    }
}
