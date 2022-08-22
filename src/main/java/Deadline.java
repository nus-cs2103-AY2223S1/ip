import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime dateTime;

    public Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s (by: %s)", super.getTaskIcon(), super.toString(), Parser.displayDateTime(this.dateTime));
    }
}
