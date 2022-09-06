package duke;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *  Represents a <code>Deadline</code>. A <code>Deadline</code> is a <code>Task</code> with a <code>by</code>
 *  attribute which indicates when the task should be completed.
 */
public class Deadlines extends Task {

    protected LocalDate by;

    /**
     * Initialises Deadline object. Parses date input into a LocalDate object.
     * @param taskName name of Deadline
     * @param by date Deadline needs to be done by
     * @throws DateTimeParseException On invalid date and time inputs.
     */
    public Deadlines(String taskName, String by) throws DateTimeParseException {
        super(taskName);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns string representation of Deadline, including deadline name, date and done status.
     * @return String representation of Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
