package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(boolean isDone, String text, LocalDateTime deadline) {
        super(isDone, text);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D] %s (by: %s)", super.toString(), this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HHmm")));
    }
}