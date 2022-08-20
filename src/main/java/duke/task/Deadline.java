package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that is due at a specific date/time.
 *
 * @author Jet Lee
 * @version CS2103T AY22/23 Sem 1
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description Description of the Deadline.
     * @param by The date/time when the Deadline is due.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor for Deadline with information on whether it is completed.
     * @param description Description of the Deadline.
     * @param isDone Whether the Event is completed.
     * @param by The date/time when the Deadline is due.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description);
        this.by = by;
        this.isDone = isDone;
    }

    /**
     * Returns the String representation of the Deadline.
     *
     * @return String detailing the Deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(), by.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    /**
     * Converts the Deadline to data to be saved.
     *
     * @return Data representing the Deadline.
     */
    @Override
    public String saveTask() {
        return String.format("D | %s | %s", super.saveTask(), by);
    }
}
