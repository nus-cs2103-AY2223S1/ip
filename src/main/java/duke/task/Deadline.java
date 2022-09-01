package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with deadline.
 */
public class Deadline extends Task {
    private final LocalDate date;

    /**
     * Returns an instance of Deadline.
     * @param isDone Is task complete.
     * @param description Description of task.
     * @param dateStr Date of deadline.
     */
    public Deadline(String isDone, String description, String dateStr) {
        super(description, isDone.equals("1"));
        LocalDate parsedDate;
        try {
            parsedDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException e) {
            // Default current date will be used.
            parsedDate = LocalDate.now();
        }
        this.date = parsedDate;
    }

    /**
     * Returns string format of deadline for saving purpose.
     * @return String format of deadline.
     */
    @Override
    public String toStringSaveFormat() {
        return String.format("D,%s,%s,%s\n", this.isDone ? "1" : "0", this.description, this.date);
    }

    /**
     * Return string representation of deadline.
     * @return String representation of deadline.
     */
    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)\n", this.isDone ? "X" : " ", this.description, this.date);
    }
}
