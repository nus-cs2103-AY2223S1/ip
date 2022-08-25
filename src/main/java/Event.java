import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime time;

    public Event(String description, String time) {
        super(description);
        this.time = LocalDateTime.parse(time, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.getDayOfWeek() + " " + time.getDayOfMonth() + " " +
                time.getMonth() + " " + time.getYear() + " " + time.getHour() + time.getMinute() + ")";
    }
}
