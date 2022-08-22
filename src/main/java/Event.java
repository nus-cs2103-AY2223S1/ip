import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDateTime atTime;

    public Event (TaskType type, String name, String timeStr) {
        super(type, name);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm, d/MM/yyyy");
        LocalDateTime atTime = LocalDateTime.parse(timeStr, formatter);
        this.atTime = atTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a, EEE, d MMM yyyy");
        return "[E]" + super.toString() +
                "(at: " + atTime.format(formatter) + ")";
    }
}
