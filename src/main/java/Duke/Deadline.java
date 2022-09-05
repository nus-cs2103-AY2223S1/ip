package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline object class.
 */
public class Deadline extends Task {
    protected String by;
    protected LocalDate date;

    /**
     * Creates a Deadline object with the description and deadline.
     *
     * @param description details of the Deadline object.
     * @param by          deadline for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.date = LocalDate.parse(by);
    }

    /**
     * Represents a Deadline object.
     *
     * @return String format of a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
