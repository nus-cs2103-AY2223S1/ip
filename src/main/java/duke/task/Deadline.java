package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  Deadline task class.
 */
public class Deadline extends Task {

    private LocalDate date;

    /**
     * Constructor of deadline task.
     *
     * @param description Description of the deadline.
     * @param date Date of the deadline, in YYYY-MM-DD format.
     * @param status Status of the deadline, 'X' is done, otherwise
     *                  Undone.
     */
    public Deadline(String description, LocalDate date, char status){
        super(description);
        this.date = date;
        if (status == 'X') {
            super.mark();
        }
    }

    /**
     * Gets the date of the deadline.
     *
     * @return The date of the deadline.
     */
    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Gets the description of the deadline.
     *
     * @return The description of the deadline.
     */
    @Override
    public String getDescription() {
        return "[D]" + super.getDescription() + "(" + this.date + ")";
    }

    /**
     * Overrides the string representation of deadline.
     *
     * @return Returns String representation of deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(" + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
