import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A task that needs to be done before a specific date/time.
 *
 * @author Lai Han Wen
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the String representation of a deadline task.
     *
     * @return the String representation of a deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +
                by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
