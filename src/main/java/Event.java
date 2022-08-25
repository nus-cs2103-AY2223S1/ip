import java.time.LocalDate;
import java.time.LocalDateTime;

public class Event extends TimeTask {

    private final static String ICON = "E";

    public Event(String description, LocalDateTime time) {
        super(description, ICON, time);
    }

    @Override
    public String toString() {
        return String.format("[E]" + "[%s] " + super.toString() + " (at: " + super.getDate() + ")", super.getStatusIcon());
    }
}
