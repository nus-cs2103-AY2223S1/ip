package Duke.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(Boolean isDone, String description, LocalDateTime deadline) {
        super(description, isDone);
        this.deadline = deadline;

    }

    @Override
    public String formatTask() {
        String dateTimePattern = "dd LLL yyyy, h.mm a";

        return String.format("[D] [%s] %s (by: %s)", this.getStatusIcon(), this.description, this.deadline.format(DateTimeFormatter.ofPattern(dateTimePattern)) );
    }

    public String formatTaskString() {
        return String.format("D|%s|%s|%s", this.isDone, this.description, this.deadline);
    }
}
