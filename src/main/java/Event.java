import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime occursAt;

    public Event(String taskName, LocalDateTime occursAt) {
        super(taskName);
        this.occursAt = occursAt;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy HH:mm");
        String stringAt = occursAt.format(formatter);
        return "[E]" + (this.done ? "[X] " : "[ ] ") + this.taskName + " (at: " + stringAt + ")";
    }

}
