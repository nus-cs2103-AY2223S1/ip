package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline object that is also a Task.
 * Inherits from the Task class.
 *
 */
public class Deadline extends Task {

    private LocalDate time;
    private static final char TYPE = 'D';

    /** Constructor for a Deadline object */
    public Deadline(String taskname, LocalDate time) {
        super(taskname);
        this.time = time;
    }

    /**
     * {@inheritDoc}
     * Converts date to "EEE, d MMM yyyy" format as well.
     *
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, d MMM yyyy");
        return String.format("[%c] %s (by: %s)", TYPE, super.toString(), this.time.format(formatter));
    }

    /**
     * {@inheritDoc}
     * Converts date to "yyyy-MMM-d" format first before writing to file
     *
     */
    @Override
    public String toSavedString() { //internal representation in save file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-d");
        return "" + TYPE + "#" + super.toSavedString() + "#" + this.time.format(formatter);
    }
}
