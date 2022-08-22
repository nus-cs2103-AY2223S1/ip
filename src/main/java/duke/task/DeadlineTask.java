package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {
    private final LocalDateTime deadline;
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yy HHmm");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("hh:mm a, MMM d, yyyy");

    public DeadlineTask(String description, LocalDateTime deadline) {
        super('D', description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.deadline.format(outputFormatter) + ")";
    }

    @Override
    public String toSaveString() {
        return super.toSaveString() + " | " + this.deadline.format(inputFormatter);
    }
}
