package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Class to represent deadline objects.
 */
public class Deadline extends Task {
    /** Date and time information of deadline object*/
    protected LocalDateTime by;

    /**
     * Constructor for an deadline object.
     * @param description Description of deadline.
     * @param by Date and time information of deadline.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }


    /**
     * Return string representation of event object, prefixed with [D], and
     * ending with the time information of the event.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"))
                + ")";
    }

    /**
     * Return string representation of deadline object for saving to file.
     */
    @Override
    public String getSavedFileFormat() {
        return "D | " + (this.isDone ? 1 : 0) + " | " + this.description
                + " | "
                + this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

}
