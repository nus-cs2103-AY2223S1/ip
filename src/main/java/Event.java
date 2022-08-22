import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    private LocalDateTime time;
    public Event(String task, LocalDateTime time) {
        super(task);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(" + this.time.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm")) + ")";
    }
}
