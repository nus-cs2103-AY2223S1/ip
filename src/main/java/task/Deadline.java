package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  A class which encapsulates the the deadline type of task
 *  @author  Chen Guanzhou
 *  @version v2
 */
public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Snoozes and postpones the deadline to a day later.
     * @return Informs the user that deadline has been snoozed for a day.
     */
    @Override
    public String snooze() {
        this.date = date.plusDays(1);
        return this + "\n" + "This deadline has been snoozed for a day!";
    }

    /**
     * String representation of a deadline object.
     * @return The string representing the object with the state of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date.format(DateTimeFormatter.ofPattern("MMM d YYY")) + ")";
    }
}