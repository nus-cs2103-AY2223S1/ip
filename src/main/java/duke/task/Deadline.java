package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that has a due date.
 *
 * @author Samsation
 * @version CS2103T AY 22/23 Sem 1
 *
 */

public class Deadline extends Task {
    private LocalDate by;

    /**
     * A constructor for Deadline.
     *
     * @param description The description of the Deadline.
     * @param isDone Has the Deadline been completed.
     * @param by The date of when the Deadline is due.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * A method that converts the Deadline into the String format required to be saved in the Storage.
     *
     * @return String formatted data of the Deadline.
     */
    @Override
    public String saveStringFormat() {
        return String.format("D | %s | %s", super.saveStringFormat(), by);
    }

    /**
     * A method that converts the Deadline into its String representation.
     *
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "\t[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
