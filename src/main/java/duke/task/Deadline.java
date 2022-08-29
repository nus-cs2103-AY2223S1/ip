package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class representing a Deadline task.
 */
public class Deadline extends Task {
    /**
     * Task of type "D".
     */
    protected final String type = "D";
    /**
     * LocalDate of when deadline is due by.
     */
    protected LocalDate by;

    /**
     * Constructor for a new Deadline.
     *
     * @param description Description of deadline.
     * @param by          LocalDate of by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets LocalDate of when deadline is due by.
     *
     * @return LocalDate by
     */
    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "[" + this.type + "]" + super.toString()
                + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
