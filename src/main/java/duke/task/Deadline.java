package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A deadline task which contains a date/time of the deadline, which inherits from Task.
 */
public class Deadline extends Task {

    /** A string representing the due date/time of the Deadline. */
    protected String by;

    /** A LocalDate representing the due date of the Deadline */
    protected LocalDate byDate;

    /**
     * Constructor for a Deadline.
     *
     * @param description The description of the Deadline.
     * @param by          The due date/time of the Deadline.
     */
    public Deadline(String description, String by) {
        super(description);
        try {
            byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("d-MM-yyyy"));
        } catch (DateTimeParseException e) {
            try {
                byDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-d"));
            } catch (DateTimeParseException e1) {
                this.by = by;
            }
        }
    }

    /**
     * Returns the string representation of the Deadline object to be stored in the file.
     *
     * @return The string representation of the Deadline object to be stored in the file.
     */
    @Override
    public String toFile() {
        String deadline = byDate != null ? byDate.format(DateTimeFormatter.ofPattern("d-MM-yyyy")) : by;
        return "D | " + (this.isDone ? "1 | " : "0 | ") + this.description + " | " + deadline + "\n";
    }

    /**
     * Returns the string representation of the Deadline object.
     *
     * @return The string representation of the Deadline object.
     */
    @Override
    public String toString() {
        String deadline = byDate != null ? byDate.format(DateTimeFormatter.ofPattern("d-MM-yyyy")) : by;
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
