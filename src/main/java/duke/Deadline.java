package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Deadline is a subclass of Task.
 */
public class Deadline extends Task {

    private LocalDateTime by;

    /**
     * Constructor for deadline.
     *
     * @param description deadline description.
     * @param by deadline date.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * String format for text file.
     *
     * @return string to be written into the text file.
     */
    @Override
    public String toStringFileFormat() {
        return "D | " + super.toStringFileFormat() + " | " + this.by;
    }

    @Override
    public LocalDateTime getDateTime() {
        return by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm")) + ")";
    }
}
