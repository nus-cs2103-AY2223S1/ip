package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *  A class which encapsulates the the deadline type of task
 *  @author  Chen Guanzhou
 *  @version v1
 */
public class Deadline extends Task {
    protected LocalDate date;

    public Deadline(String description, LocalDate date) {
        super(description);
        this.date = date;
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