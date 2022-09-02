import java.time.LocalDateTime;

public class Deadline extends Task {
    protected LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String encode() {
        return "D | " + super.encode() + "| " + this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: " + deadline.format(Task.DATE_TIME_DISPLAY_FORMATTER) + ")";
    }
}
