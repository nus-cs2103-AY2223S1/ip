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
    private final char type = 'D';

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
        return String.format("[%c] %s (by: %s)", this.type, super.toString(), this.time.format(formatter));
    }

    /**
     * {@inheritDoc}
     * Converts date to "yyyy-MMM-d" format first before writing to file
     *
     */
    @Override
    public String toSavedString() { //internal representation in save file
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-d");
        return "" + this.type + "#" + super.toSavedString() + "#" + this.time.format(formatter);
    }
}
