import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class is a subclass of Task. It represents
 * a task in real life that has a deadline associated.
 */
public class Deadline extends Task {
    public LocalDate date;

    public Deadline(String taskName) throws IndexOutOfBoundsException, DukeException {
        super(taskName.substring(9, taskName.indexOf(" /by")));
        try {
            this.date = LocalDate.parse(taskName.substring(taskName.indexOf(" /by") + 5));
        } catch (DateTimeParseException e) {
            throw new DukeException("Your date is rubbish");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.date.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public LocalDate getDate() {
        return this.date;
    }
}
