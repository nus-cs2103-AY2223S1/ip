package puke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A type of task, called a Deadline
 */
public class Deadline extends Task {
    /**
     * date to do task by, stored as a LocalDate
     */
    protected LocalDateTime by;

    /**
     * Creates a Deadline task
     * @param description what the task is
     * @param by LocalDate object of when the deadline is
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets string representation of Deadline
     * @return string representation of Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm")) + ")";
    }

    /**
     * Gets the format to save Deadline on hard disk
     * @return String representation of Deadline save format
     */
    @Override
    public String saveFormat() {
        if (this.isDone) {
            return "D | 1 | " + this.description + " | " + this.by;
        } else {
            return "D | 0 | " + this.description + " | " + this.by;
        }
    }
}
