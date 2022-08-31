package duke;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline class which is a subclass of Task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructor for Deadline class.
     *
     * @param description Description of the deadline.
     * @param by Date of the deadline in String format.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    /**
     * @inheritDoc
     */
    @Override
    public String toSavedString() {
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + this.by;
    }
}