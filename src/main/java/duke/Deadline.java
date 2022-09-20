package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Deadline class represents a type of Task that has a due date.
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * Constructor for Deadline.
     *
     * @param description the String that describes the task
     * @param by the LocalDateTime of the deadline
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the task in a specific format to save it in the text file.
     *
     * @return the string that represents the task in a specific format
     */
    @Override
    public String getDataFormat() {
        return String.format("D // %s // %s // %s", getStatusIcon(), description, by);
    }

    /**
     * Returns the string representation of the Task.
     *
     * @return a string that represents the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)) + ")";
    }
}
