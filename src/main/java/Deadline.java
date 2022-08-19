import java.time.LocalDateTime;

/**
 * A task that needs to be done before a specific date/time.
 */
public class Deadline extends Task {
    private final LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(String description, String deadline) {
        this(description, LocalDateTime.parse(deadline, Task.DATE_TIME_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                Task.DATE_TIME_FORMATTER.format(deadline)
        );
    }
}
