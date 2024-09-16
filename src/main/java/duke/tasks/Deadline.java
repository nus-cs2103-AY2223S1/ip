package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Command class for adding Deadlines.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructor for Deadline Command.
     *
     * @param description Task description.
     * @param by When the Deadline Task must be completed by.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String taskMemo() {
        return "D" + super.taskMemo() + " | " + this.by.toString();
    }
}
