import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
        return dTF.format(deadline).toString();
    }

    @Override
    public String toString() {
        return String.format("[D] %s %s (by: %s)", getStatusIcon(), getDescription(), getDeadline());
    }
}
