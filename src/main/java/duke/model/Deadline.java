package duke.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Task with the activity and deadline specified.
 */
public class Deadline extends Task {

    protected LocalDate date;

    /**
     * A constructor for a Deadline.
     *
     * @param description The details of the activity.
     * @param date The date of the activity.
     */
    public Deadline(String description, String date) {
        super(description);
        this.date = LocalDate.parse(date);
    }

    /**
     * Returns a formatted string of a Deadline to be stored in the storage.
     *
     * @return A formatted string of a Deadline for storage.
     */
    @Override
    public String toStorage() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + description + " | " + this.date + "\n";
    }

    /**
     * Returns a string representation of a Deadline.
     *
     * @return A string representing a Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("d MMM YYYY")) + ")";
    }
}
