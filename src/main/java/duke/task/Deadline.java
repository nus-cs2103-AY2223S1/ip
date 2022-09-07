package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {
    private LocalDate by;

    /**
     * Constructs a deadline task with task description and deadline date.
     *
     * @param description description of the task
     * @param by deadline of the task
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns this Deadline Task in CSV format.
     *
     * @return CSV representation of this Deadline Task
     */
    @Override
    public String toCsv() {
        return "D," + super.toCsv() + "," + this.by + "\n";
    }

    /**
     * Returns a string representation of this Deadline Task.
     *
     * @return a string representation of this Deadline Task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }
}
