package roger.tasks;

import java.time.LocalDate;

/**
 * Encapsulates a deadline.
 */
public class Deadline extends Task {
    private LocalDate date;

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
     * Returns true if the deadline falls on the given date.
     *
     * @param date The date to check with.
     * @return true if the deadline falls on the given date.
     */
    public boolean isOnDate(LocalDate date) {
        return this.date.equals(date);
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

    /**
     * Returns the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }
}
