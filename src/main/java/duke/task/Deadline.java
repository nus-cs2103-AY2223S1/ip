package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Initializes Deadline object
     *
     * @param description Description of deadline task
     * @param by          Deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
        this.type = Type.DEADLINE;
    }

    @Override
    public String toDataString() {
        return "D" + super.toDataString() + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }


}
