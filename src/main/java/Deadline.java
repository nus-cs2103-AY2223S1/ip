import java.time.LocalDateTime;

/**
 * This class inherits from the abstract Task class
 * and encapsulates the logic of a Deadline task.
 */
public class Deadline extends Task {
    /* Due Date field */
    private LocalDateTime dueDate;

    /**
     * Constructor for the Deadline Task.
     * @param description description of the task.
     */
    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        this.dueDate = dueDate;
    }

    /**
     * Overriden toString method for the Deadline Task.
     * @return String representation of the Deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate.format(OUTPUT_DATE_FORMAT) + ")";
    }

    /**
     * Override save format method from Task class.
     *
     * @return formatted String for the Event task.
     */
    @Override
    public String saveFormat() {
        return String.format("D | %s | %s", super.saveFormat(), this.dueDate);
    }
}
