package pikachu.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Represents a task type. A <code>Deadline</code> object corresponds to
 * a task with certain deadline.
 */
public class Deadline extends Task {

    private final LocalDate by;

    /**
     * Initialises deadline with default not done.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Initialises deadline with isDone indicated.
     */
    public Deadline(String description, boolean isDone, LocalDate by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the name of deadline "D".
     * @return "D" to represent deadline.
     */
    public String getName() {
        return "D";
    }

    /**
     * Returns the date of the deadline.
     * @return date of the deadline formatted d MMMM yyyy.
     */
    public String getTiming() {
        return this.by.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
    }

    /**
     * Returns the string of the deadline when writing into task list.
     * @return deadline written format in task list.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getTiming() + ")";
    }
}
