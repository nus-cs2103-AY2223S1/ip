import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Event extends Task {
    protected String timeString;
    protected LocalDate time;

    public Event(String description, String time) {
        super(description);
        this.timeString = time;
        this.time = LocalDate.parse(time);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toWrite() {
        return String.format("E~%s~%s~%s", (isDone ? "1" : "0"), description.trim(), time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
