package sakura;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Constructor for a deadline.
     *
     * @param description description of the task.
     * @param by The deadline in datetime format for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Convert the deadline into the specific string for data saving.
     *
     * @return the string representation in the specific format for database
     */
    @Override
    public String stringifyTask() {
        String timeFormat = this.by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        return String.format("D|%d|%s|%s", this.isDone ? 1 : 0, this.description, timeFormat);
    }

    /**
     * Return the string representation of the deadline.
     *
     * @return string representation of the deadline
     */
    @Override
    public String toString() {
        String timeFormat = this.by.format(DateTimeFormatter.ofPattern("HH:mm, dd MMM yyyy"));
        return "(DEADLINE)" + super.toString() + " (by: " + timeFormat + ")";
    }
}
//[31m
//[0m