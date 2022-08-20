import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    LocalDate start;
    LocalDate end;

    public Event(String description,String start, String end) {
        super(description);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public String writeString() {
        return String.format("E | %d | %s | %s | %s", super.isDone ? 1 : 0, super.description, 
                start, end);
    }

    @Override
    public String toString() {
        return String.format("[E]%s at: %s to %s", super.toString(), start.toString(), end.toString());
    }
}
