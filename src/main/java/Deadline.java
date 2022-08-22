import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task  {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    LocalDateTime getTime() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
