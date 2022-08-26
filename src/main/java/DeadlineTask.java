import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a time-sensitive task that has a deadline.
 */
public class DeadlineTask extends Task{

    /** The deadline for the task. */
    private LocalDateTime deadline = null;

    /**
     * Constructor for an deadline task.
     * @param description A description of the deadline
     * @param deadline The time at which the task will expire
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public DeadlineTask(String rawInput) {
        super(rawInput.split("/by")[0]);
        this.deadline = rawInput.split("/by")[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("d/MMM/yy HH:mm")));
    }
}
