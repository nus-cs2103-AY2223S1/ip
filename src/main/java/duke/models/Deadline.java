package duke.models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline.
 *
 * @author Zhu Yuanxi
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the deadline.
     * @param by The date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a Deadline object.
     *
     * @param description The description of the deadline task.
     * @param by The date of the deadline.
     * @param isDone The status of the deadline task.
     */
    public Deadline(String description, LocalDate by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Formats the string representation of the deadline object for save.
     *
     * @return The string representation of a deadline object for save.
     */
    public String formatForSave() {
        return "D | " + super.formatForSave() + " | " + by;
    }

    /**
     * Formats the string representation of the deadline object for display.
     *
     * @return The string representation of a deadline object for display.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")\n";
    }
}
