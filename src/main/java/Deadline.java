import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class that inherits from Task.
 */
public class Deadline extends Task {

    protected String by;
    protected LocalDate date;

    /**
     * Constructor of Deadline class.
     *
     * @param description the description of the task
     * @param by          a String that represents date of deadline
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructor of Deadline class with datetime.
     *
     * @param description the description of the deadline task
     * @param date          a LocalDate that represents date of deadline
     */
    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
    }

    /**
     * Returns a String that represents the Deadline.
     *
     * @return a String that represents the Deadline
     */
    @Override
    public String toString() {
        if (this.date == null) {
            return "[D]" + super.toString() + " (by: " + by + ")";
        } else {
            String d = this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            return "[D]" + super.toString() + " (by: " + d + ")";
        }
    }
}
