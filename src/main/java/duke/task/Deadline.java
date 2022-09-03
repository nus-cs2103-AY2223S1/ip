package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class for a deadline task.
 *
 * @author dexter-sim
 * @version 0.1
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Creates a deadline with specified description and date.
     *
     * @param description The description of the deadline to be created.
     * @param by The date of the deadline.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a deadline with specified description, completeness and date.
     *
     * @param description The description of the deadline to be created.
     * @param isDone If the deadline is completed.
     * @param by The date of the deadline.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    private String printDate() {
        return DateTimeFormatter.ofPattern("MMM dd yyyy").format(by);
    }

    /**
     * Returns the deadline in a string format to be saved in a local file.
     *
     * @return A string corresponding to the deadline.
     */
    @Override
    public String stringify() {
        return String.format("%s | %s | %s", "D", super.stringify(), by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), printDate());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (obj instanceof Deadline) {
            Deadline deadline = (Deadline) obj;
            return super.equals(deadline) && deadline.by.equals(by);
        } else {
            return false;
        }
    }
}
