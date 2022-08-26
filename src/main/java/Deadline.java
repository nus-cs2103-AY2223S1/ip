import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime dueBy;

    public Deadline(String taskName, LocalDateTime dueBy) {
        super(taskName);
        this.dueBy = dueBy;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringBy = dueBy.format(formatter);
        return "[D]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (by: " + stringBy + ")";
    }
}
