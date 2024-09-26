package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class to model a Deadline object.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a new instance of Deadline.
     *
     * @param description the deadline description
     * @param by the local date of the deadline
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a String representation for storage purpose.
     *
     * @return String representation for storage purpose
     */
    @Override
    public String toStorageString() {
        return "D" + super.toStorageString() + " | "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Returns a String representation for Deadline.
     *
     * @return String representation for Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    /**
     * Returns true if obj is equal to this instance else false
     *
     * @param obj object to be compared with
     * @return true if obj is equal to this instance in its description, by, and isDone else false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Deadline) {
            Deadline temp = (Deadline) obj;
            return temp.description.equals(this.description) && temp.by.equals(this.by)
                    && temp.isDone == this.isDone;
        }
        return false;
    }
}
