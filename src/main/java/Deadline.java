import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Deadlines are tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String description, String by) throws InvalidDeadlineException {
        super(description);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    public String getFormattedDate() {
        return this.by.format(DateTimeFormatter.ofLocalizedDate(Task.DATE_FORMAT));
    }
    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " +  this.getFormattedDate()
                + ")";
    }
}