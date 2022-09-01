package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for deadline.
     *
     * @param detail String
     */
    public Deadline(String detail, String by) {
        super(detail);
        this.by = LocalDate.parse(by);
    }

    /**
     * Constructor for deadline.
     *
     * @param detail
     * @param isDone
     * @param by
     */
    public Deadline(String detail, boolean isDone, String by) {
        super(detail, isDone);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns String form of the task
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * Returns String to be stored in the hardware list.
     * @return String
     */
    @Override
    public String storedData() {
        return "D" + "|" + super.storedData() + "|" + by.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }
}
