package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a Task that represents a deadline.
 *
 * @author John Russell Himawan
 * @version CS2103T AY22/23 Sem 1
 */
public class Deadline extends Task {
    private static final LocalDate TODAY = LocalDate.now();
    protected LocalDate by;

    /**
     * Constructor for Deadline.
     *
     * @param description String representation of description.
     * @param by Date when the deadline is due.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns the date of the deadline.
     *
     * @return LocalDate of deadline.
     */
    public LocalDate getBy() {
        return this.by;
    }

    /**
     * Checks if the task needs a reminder.
     *
     * @return Boolean representing whether the task needs a reminder.
     */
    @Override
    public boolean isNeedReminder() {
        LocalDate lastReminderDate = TODAY.plusDays(7);
        return getBy().compareTo(TODAY) >= 0 && getBy().compareTo(lastReminderDate) <= 0;
    }

    /**
     * Returns the String representation of Deadline.
     *
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
