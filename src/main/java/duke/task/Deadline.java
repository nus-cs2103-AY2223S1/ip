package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline class represents the deadline task created by user.
 */
public class Deadline extends Task {
    private final LocalDate deadline;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor of the Deadline class.
     * Sets the description of the deadline and the deadline date
     * to the local variables.
     *
     * @param description Description of the deadline task.
     * @param deadline Deadline for the deadline task.
     */
    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline.format(dtf) + ")";
    }

    /**
     * Returns the string formatting for the Deadline task
     * to be stored in the txt file.
     *
     * @return The string formatting of the deadline.
     */
    @Override
    public String stringFormatting() {
        return "D" + super.stringFormatting() + " # " + this.deadline;
    }
}
