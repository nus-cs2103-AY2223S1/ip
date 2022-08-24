import java.time.LocalDateTime;

/**
 * Represents a deadline, a type of task
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    /**
     * Constructs a deadline with some description and a datetime string to indicate
     * the deadline
     *
     * @param description The specified description.
     * @param deadline    The specified datetime string for the deadline.
     */
    Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return Task.dateTimeDisplayFormatter.format(this.deadline);
    }

    @Override
    public String toString() {
        return "[" + TaskType.D + "]" + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
