import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task{
    private LocalDateTime time;

    public Event(String description, LocalDateTime time) {
        super(description);
        this.time = time;
    }

    LocalDateTime getTime() {
        return this.time;
    }


    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + ")";
    }
}
