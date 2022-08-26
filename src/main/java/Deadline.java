import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, String deadlineStr) {
        super(description);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        this.deadline = LocalDateTime.parse(deadlineStr, formatter);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDeadlineStr() + ")";
    }

    public String getDeadlineStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE, hh:mma dd MMMM yyyy");
        return this.deadline.format(formatter);
    }
}
