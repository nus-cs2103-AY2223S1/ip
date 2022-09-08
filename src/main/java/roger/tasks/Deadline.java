package roger.tasks;

import java.time.LocalDate;

/**
 * Encapsulates a deadline.
 */
public class Deadline extends Task {

    /**
     * Create a Deadline.
     *
     * @param name The deadline name.
     * @param date The due date.
     */
    public Deadline(String name, LocalDate date) {
        super(name);
        this.date = date;
    }

    /**
     * String representation of the deadline.
     *
     * @return The string representation of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.toString() + ")";
    }
}
